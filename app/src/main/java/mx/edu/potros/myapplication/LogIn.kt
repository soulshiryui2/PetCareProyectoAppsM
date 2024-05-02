package mx.edu.potros.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import mx.edu.potros.myapplication.databinding.ActivityLogInBinding
import mx.edu.potros.myapplication.databinding.ActivityMainBinding

class LogIn : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLogInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.buttonLogin.setOnClickListener{
            val mEmail = binding.etCorreo.text.toString()
            val mPassword = binding.etPassword.text.toString()
            ocultarTeclado()
            when{
                mEmail.isEmpty() || mPassword.isEmpty()->{
                    Toast.makeText(baseContext,"Mail o contraseña incorrecta.",
                        Toast.LENGTH_SHORT).show()
                }else ->{
                SignIn(mEmail,mPassword)
            }
            }
        }
    }

    private fun SignIn(email:String,password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    reaload()
                    //val user = auth.currentUser
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Contrasena equivocada, Verifica tu contrasena.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
    private fun reaload(){
        val intent = Intent(this, principal::class.java)
        this.startActivity(intent)
    }
    private fun ocultarTeclado() {
        val view: View? = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}