package com.example.chat_app.login

import androidx.databinding.ObservableField
import com.example.chat_app.base.BaseViewModel
import com.example.chat_app.database.signIn
import com.example.chat_app.model.AppUser
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginViewModel : BaseViewModel<Navigator>() {
    val email = ObservableField<String>()
    val emailError = ObservableField<String>()
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()

    var auth = Firebase.auth

    fun login() {
        if (validate()) {
            loginWithFirebaseAuth()
        }
    }
    fun loginWithFirebaseAuth() {
        showLoading.value = true
        auth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener {
                showLoading.value = false
                if (!it.isSuccessful) {
                    messageLiveData.value = it.exception?.localizedMessage
                } else {
                    //messageLiveData.value = "Successful Login"
                    //navigator?.openHomeScreen()
                    checkUserFromFireStore(it.result.user?.uid)
                }
            }
    }

    private fun checkUserFromFireStore(uid: String?) {
        showLoading.value = true
           signIn(uid!!, OnSuccessListener {
           showLoading.value = false
               val user = it.toObject(AppUser::class.java)
               if(user == null){
                   messageLiveData.value = "Inavalid Email And Passeord"
               }else {
                   navigator?.openHomeScreen()
               }

           }
               , OnFailureListener {
                   showLoading.value = false
                   messageLiveData.value = it.localizedMessage
               })
    }

    fun validate(): Boolean {
        var isValid = true
        if (email.get().isNullOrBlank()) {
            emailError.set("Please! Enter Your Email")
            isValid = false
        } else {
            emailError.set(null)
        }
        if (password.get().isNullOrBlank()) {
            passwordError.set("Please! Enter Your Password")
            isValid = false
        } else {
            passwordError.set(null)
        }
        return isValid
    }

    fun openRegisterScreen(){
        navigator?.openRegistrationScreen()
    }
}