package mx.edu.potros.myapplication

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import mx.edu.potros.myapplication.R

class PerfilMascota : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var recyclerViewMascotas: RecyclerView
    private lateinit var mascotasAdapter: MascotasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_mascota)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance().reference
        mascotasAdapter = MascotasAdapter()

        // Inicializar RecyclerView
        recyclerViewMascotas = findViewById(R.id.recyclerViewMascotas)

        // Configurar RecyclerView
        recyclerViewMascotas.apply {
            layoutManager = LinearLayoutManager(this@PerfilMascota)
            adapter = mascotasAdapter
        }

        // Obtiene el UID del usuario actual
        val userId = auth.currentUser?.uid

        // Escucha los cambios en los datos del usuario actual en Firebase Realtime Database
        userId?.let { uid ->
            val userRef = database.child("usuarios").child(uid)
            userRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        // Obtiene las mascotas asociadas al usuario
                        val mascotasList = mutableListOf<Mascota>()
                        for (mascotaSnapshot in snapshot.child("mascotas").children) {
                            val mascota = mascotaSnapshot.getValue(Mascota::class.java)
                            mascota?.let {
                                mascotasList.add(it)
                            }
                        }

                        // Actualiza el adaptador con la lista de mascotas
                        mascotasAdapter.submitList(mascotasList)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Maneja errores de lectura de datos
                }

            })
        }

        val buttonVolver: Button = findViewById(R.id.buttonVolver)
        buttonVolver.setOnClickListener {
            finish()
        }
    }
}


