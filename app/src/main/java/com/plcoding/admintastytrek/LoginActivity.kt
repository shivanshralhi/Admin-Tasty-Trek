
package com.plcoding.admintastytrek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.plcoding.admintastytrek.databinding.ActivityLoginBinding
import com.plcoding.admintastytrek.model.UserModel

class LoginActivity : AppCompatActivity() {
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private val binding:ActivityLoginBinding by lazy{
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initialize Firebase Auth
        auth = Firebase.auth
        //initialize Firebase database
        database = Firebase.database.reference
        binding.btnLogin.setOnClickListener {
            //get text from edit text
            email = binding.etEmail.text.toString().trim()
            password = binding.etPassword.text.toString().trim()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please fill all Details", Toast.LENGTH_SHORT).show()
            } else {
                createUserAccount(email, password)
            }
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)

        }
        binding.tvDontHaveAccount.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)

        }
    }

    private fun createUserAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user: FirebaseUser? = auth.currentUser
                saveUserData()
                updateUi(user)
            }
            else {
                Toast.makeText(this, "Authentification failed", Toast.LENGTH_SHORT).show()
                Log.d(
                    "Account",
                    "createUserAccount : Authentification failed",
                    task.exception
                )


            }
        }
            }

    private fun updateUi(user: FirebaseUser?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }

    private fun saveUserData() {
        //get text from edit text
        email = binding.etEmail.text.toString().trim()
        password = binding.etPassword.text.toString().trim()

        val user = UserModel(email,password)
        val userId:String? = FirebaseAuth.getInstance().currentUser?.uid
        userId?.let {

            database.child("user").child(it).setValue(user)

        }

    }


}


