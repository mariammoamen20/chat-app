package com.example.chat_app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    var id: String? = null,
    val roomName: String? = null,
    val roomDescription: String? = null,
    val categoryId: String? = null,
) :Parcelable{


    companion object {
        const val COLLECTION_NAME = "Rooms" //collection name in fierstore
    }
}