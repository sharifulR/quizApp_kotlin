package com.wbsoft.quiz.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.wbsoft.quiz.MainActivity
import com.wbsoft.quiz.R
import com.wbsoft.quiz.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)

// Initialize Firebase Auth
        auth = Firebase.auth
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {
            if (validateData()){
                login()
            }

        }
        binding.signupTextView.setOnClickListener {
            startActivity(Intent(this,SignupActivity::class.java))
            finish()
        }
    }

    private fun login() {
        val email=binding.emailETId.text.toString()
        val password=binding.passwordETId.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                    updateUI(null)
                }
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
    private fun updateUI(user: FirebaseUser?) {
        if (user!=null){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            reload()
        }
    }

    private fun reload() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}