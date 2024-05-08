package mx.edu.potros.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Button


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonRegistrarse = findViewById<Button>(R.id.btn_registro)
        val buttonIniciarSesion = findViewById<Button>(R.id.btn_iniciarSesion)


        buttonRegistrarse.setOnClickListener {
            val intent = Intent(this, Registrarse::class.java)
            startActivity(intent)
        }

        buttonIniciarSesion.setOnClickListener {
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }


    }
}
