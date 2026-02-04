package com.plcoding.admintastytrek

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.plcoding.admintastytrek.databinding.ActivitySignUpBinding
import com.plcoding.admintastytrek.model.UserModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var email : String
    private lateinit var password : String
    private lateinit var userName : String
    private lateinit var nameOfRestaurant : String
    private lateinit var database : DatabaseReference

    private val binding: ActivitySignUpBinding by lazy{
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initialize Firebase Auth
        auth = Firebase.auth

        //initialize Firebase database
        database = Firebase.database.reference
        binding.btnSignUpLogin.setOnClickListener {
            //get text value from text editor
            userName = binding.etName.text.toString().trim()
            nameOfRestaurant= binding.etRestaurants.text.toString().trim()
            email = binding.etSignUpEmail.text.toString().trim()
            password = binding.etSignUpPassword.text.toString().trim()
            if (userName.isBlank() || nameOfRestaurant.isBlank() || email.isBlank() || password.isBlank()){
                Toast.makeText(this,"Please fill all Details",Toast.LENGTH_SHORT).show()
            }
            else{
                createAccount(email,password)

            }
//            val intent = Intent(this,MainActivity::class.java)
//            startActivity(intent)

        }
        binding.tvAlreadyHaveAnAccount.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)

        }


        val locationList = arrayOf("Jaipur","Udaipur","Prayagraj","Agra")
        val adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,locationList)
        val autoCompleteTextView = binding.listOfLocation
        autoCompleteTextView.setAdapter(adapter)

    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Account created successfully",Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent = Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this,"Account creation Failed",Toast.LENGTH_SHORT).show()
                Log.d("Account","createAccount:Failure",task.exception)
            }
        }

    }

    private fun saveUserData() {
        //get text value from text editor
        userName = binding.etName.text.toString().trim()
        nameOfRestaurant= binding.etRestaurants.text.toString().trim()
        email = binding.etSignUpEmail.text.toString().trim()
        password = binding.etSignUpPassword.text.toString().trim()
        val user= UserModel(userName,nameOfRestaurant,email,password)
        val userId:String = FirebaseAuth.getInstance().currentUser!!.uid
        //save user data into firebase
        database.child("user").child(userId).setValue(user)
    }
}