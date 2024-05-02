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
        // Aquí obtienes la referencia al botón de "Registrarse" por su ID
        val buttonRegistrarse = findViewById<Button>(R.id.btn_registro)

        // Configuras el OnClickListener para el botón
        buttonRegistrarse.setOnClickListener {
            // Cuando se hace clic en el botón, se inicia la actividad Registrarse
            val intent = Intent(this, Registrarse::class.java)
            startActivity(intent)
            // No es necesario llamar a finish() aquí ya que quieres permanecer en la actividad actual
        }


    }
}
