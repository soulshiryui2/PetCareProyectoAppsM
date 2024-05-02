package mx.edu.potros.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        // Obtener una referencia al botón "Agregar nuevo"
        val btnAgregarMascota = findViewById<Button>(R.id.btn_agregarMascota)

        // Configurar el OnClickListener para el botón "Agregar nuevo"
        btnAgregarMascota.setOnClickListener {
            // Crear un Intent para iniciar la nueva actividad
            val intent = Intent(this, AgregarMascota1::class.java)
            startActivity(intent)
        }
    }

}