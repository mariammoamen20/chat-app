package com.example.chat_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.chat_app.home.HomeActivity
import com.example.chat_app.login.LoginActivity
import com.example.chat_app.registration.RegistrationActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.chat_app.database.signIn
import com.example.chat_app.model.AppUser
import com.example.chat_app.objects.DataUtils

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.myLooper()!!).postDelayed({
           checkLoggedInUser()
            finish()
        },2000)
    }

     fun checkLoggedInUser() {
        val firebaseUser = Firebase.auth.currentUser
        if(firebaseUser == null){
            startLoginActivity()
        }else {
            //retrieve user from firestore
                signIn(firebaseUser.uid,
                    onSuccessListener = {
                     val user = it.toObject(AppUser::class.java)
                        DataUtils.user = user
                    },
                    onFailureListener = {
                        startLoginActivity()
                      //Toast.makeText(this,"Can Not Login",Toast.LENGTH_SHORT).show()
                    })
            startHomeActivity()
        }
    }
    private fun startHomeActivity() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
    private fun startLoginActivity() {
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}