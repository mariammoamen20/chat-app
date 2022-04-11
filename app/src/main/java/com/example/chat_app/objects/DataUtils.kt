package com.example.chat_app.objects

import com.example.chat_app.model.AppUser
import com.google.firebase.firestore.auth.User

object DataUtils {
    var user : AppUser?=null
    var firebaseUser : User?=null
}