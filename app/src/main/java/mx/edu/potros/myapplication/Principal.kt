package mx.edu.potros.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        val btnAgregarMascota = findViewById<Button>(R.id.btn_agregarMascota)

        btnAgregarMascota.setOnClickListener {
            val intent = Intent(this, AgregarMascota::class.java)
            startActivity(intent)
        }
    }

}