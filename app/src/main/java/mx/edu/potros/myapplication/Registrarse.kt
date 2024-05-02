package mx.edu.potros.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.edu.potros.myapplication.databinding.ActivityRegistrarseBinding

class Registrarse : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistrarseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //aut
        auth=Firebase.auth

        binding.saveButton.setOnClickListener{
            val email=binding.etCorreo.text.toString()
            val password=binding.etContrasenia.toString()

            if (checkAllField()){
             auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                 //si es correcto la cuenta se crea
                 //si no pues no se crea la cuenta nueva
                 if (it.isSuccessful){
                     auth.signOut()
                     Toast.makeText(this, "la cuenta a sido creada con exito",Toast.LENGTH_SHORT).show()
                 }else{
                     //cuenta no creada
                     Log.e("error:",it.exception.toString())
                 }
             }
            }

        }



    }
    private fun checkAllField(): Boolean {
        val email=binding.etCorreo.text.toString()
        if (binding.etCorreo.text.toString()==""){
            binding.etCorreo.error= "Se requiere este campo"
            return false
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etCorreo.error= "Revisa el formato y vuelve a intentar"

        }
        //La contrasena tiene que tener al menos 6 digitos entre letras y numeros
        if (binding.etContrasenia.length()<=6){
            binding.etContrasenia.error= "Ingresa una contrasena de al menos 6 digitos"
            return false
        }
        if (binding.etContrasenia.text.toString()==""){
            binding.etContrasenia.error= "este campo es requerido"
            return false
        }
        if (binding.etUsuario.text.toString()==""){
            binding.etUsuario.error= "Usuario incorrecto"
            return false
        }


       return true
    }
}