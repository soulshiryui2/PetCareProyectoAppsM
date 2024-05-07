package mx.edu.potros.myapplication

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException
import java.util.*

class AgregarMascota1 : AppCompatActivity() {

    private lateinit var editTextNombreMascota: EditText
    private lateinit var buttonAgregarFoto: Button

    // Referencia a la base de datos en tiempo real de Firebase
    private lateinit var database: DatabaseReference
    // Referencia al almacenamiento de Firebase para guardar imágenes
    private lateinit var storage: FirebaseStorage

    private val PICK_IMAGE_REQUEST = 71

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_mascota1)

        editTextNombreMascota = findViewById(R.id.editTextNombreMascota)
        buttonAgregarFoto = findViewById(R.id.buttonAgregarFoto)

        // Inicializa la instancia de Firebase Database
        database = FirebaseDatabase.getInstance().reference
        // Inicializa la instancia de Firebase Storage
        storage = FirebaseStorage.getInstance()

        buttonAgregarFoto.setOnClickListener {
            // Inicia la galería de imágenes para seleccionar una foto
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Selecciona una imagen"), PICK_IMAGE_REQUEST)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            try {
                // Obtiene la URI de la imagen seleccionada
                val filePath = data.data
                // Convierte la URI en un ByteArray
                val inputStream = contentResolver.openInputStream(filePath!!)
                val selectedImage = BitmapFactory.decodeStream(inputStream)
                // Guarda la imagen en Firebase Storage
                val imageName = UUID.randomUUID().toString() + ".jpg"
                val imageRef = storage.reference.child("images/$imageName")
                imageRef.putFile(filePath)
                    .addOnSuccessListener { // La imagen se subió exitosamente, ahora obtén su URL de descarga
                        imageRef.downloadUrl.addOnSuccessListener { uri ->
                            val imageUrl = uri.toString()
                            // Guarda el nombre de la mascota y la URL de la imagen en Firebase Realtime Database
                            val nombreMascota = editTextNombreMascota.text.toString()
                            val mascotaId = database.child("mascotas").push().key
                            val mascota = Mascota(nombreMascota, imageUrl)
                            mascotaId?.let {
                                database.child("mascotas").child(it).setValue(mascota)
                            }
                            // Vuelve a la actividad anterior
                            finish()
                        }
                    }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
