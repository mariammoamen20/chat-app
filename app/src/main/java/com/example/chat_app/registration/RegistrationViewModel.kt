package com.example.chat_app.registration

import android.util.Log
import androidx.databinding.ObservableField
import com.example.chat_app.base.BaseViewModel
import com.example.chat_app.database.addUserToFireStore
import com.example.chat_app.model.AppUser
import com.example.chat_app.objects.DataUtils
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegistrationViewModel : BaseViewModel<Navigator>() {
    //first name
    val firstName = ObservableField<String>()
    val firstNameError = ObservableField<String>()

    //last name
    val lastName = ObservableField<String>()
    val lastNameError = ObservableField<String>()

    //user name
    val userName = ObservableField<String>()
    val userNameError = ObservableField<String>()

    //email
    val email = ObservableField<String>()
    val emailError = ObservableField<String>()

    //password
    val password = ObservableField<String>()
    val passwordError = ObservableField<String>()

    val auth = Firebase.auth


    fun createAccount() {
        if (isValidate()) {
            addAccountToFirebase()
        }
    }

    fun addAccountToFirebase() {
        showLoading.value = true
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnCompleteListener {
                showLoading.value = false
                if (!it.isSuccessful) {
                    //show error message
                    messageLiveData.value = it.exception?.localizedMessage
                    //Log.e("firebase","failed"+it.exception?.localizedMessage)
                } else {
                    //show success message
                    //messageLiveData.value = "Successful Registration"
                    //Log.e("firebase","successful registraion")
                    createFireStoreUser(it.result.user?.uid)
                }
            }
    }

    private fun createFireStoreUser(uid: String?) {
        showLoading.value = true
        val user = AppUser(
            id = uid,
            firstName = firstName.get(),
            lastName = lastName.get(),
            userName = userName.get(),
            email = email.get()
        )
        addUserToFireStore(user, OnSuccessListener {
            showLoading.value = false
            DataUtils.user = user
            navigator?.openHomeScreen()
        }, OnFailureListener {
            showLoading.value = false
        })
    }

    fun isValidate(): Boolean {
        var isValid = true
        if (firstName.get().isNullOrBlank()) {
            Log.e("fn", "" + firstNameError)
            firstNameError.set("Please! Enter First Name ")
            isValid = false
        } else {
            isValid = true
            firstNameError.set(null)
        }

        if (lastName.get().isNullOrBlank()) {
            lastNameError.set("Please! Enter Last Name ")
            isValid = false
        } else {
            lastNameError.set(null)
        }

        if (userName.get().isNullOrBlank()) {
            userNameError.set("Please! Enter User Name ")
            isValid = false

        } else {
            userNameError.set(null)
        }

        if (email.get().isNullOrBlank()) {
            emailError.set("Please! Enter E-Mail ")
            isValid = false
        } else {
            emailError.set(null)
        }

        if (password.get().isNullOrBlank()) {
            passwordError.set("Please! Enter Password ")
            isValid = false

        } else {
            passwordError.set(null)
        }
        return isValid
    }

}