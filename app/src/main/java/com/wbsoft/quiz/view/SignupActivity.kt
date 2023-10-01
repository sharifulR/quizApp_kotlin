package com.wbsoft.quiz.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wbsoft.quiz.MainActivity
import com.wbsoft.quiz.databinding.ActivitySignupBinding


class SignupActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignupBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignupBinding.inflate(layoutInflater)

        auth = Firebase.auth

        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            if (validateData()){
                signup()
            }

        }
        binding.signInTextView.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
    private fun signup() {
        val email=binding.emailETId.text.toString()
        val password=binding.passwordETId.text.toString()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.", Toast.LENGTH_SHORT,).show()
                    updateUI(null)
                }
            }

    }

    private fun updateUI(user: FirebaseUser?) {
        if (user!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun validateData(): Boolean {

        if (binding.emailETId.getText().toString().isEmpty()){
            binding.emailETId.setError("Enter Email address")
            return false
        }
        if (binding.passwordETId.getText().toString().isEmpty()){
            binding.passwordETId.setError("Enter Password")
            return false
        }
        return true
    }
}