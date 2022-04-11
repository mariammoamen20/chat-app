package com.example.chat_app.database

import com.example.chat_app.model.AppUser
import com.example.chat_app.model.Message
import com.example.chat_app.model.Room
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun getCollection(collectionName: String): CollectionReference {
    val db = Firebase.firestore
    return db.collection(collectionName)
}

fun addUserToFireStore(
    user: AppUser,
    onSuccessListener: OnSuccessListener<Void>,
    onFailureListener: OnFailureListener
) {
    val userCollection = getCollection(AppUser.COLLECTION_NAME)
    val userDoc = userCollection.document(user.id!!)
    userDoc.set(user)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}

fun signIn(
    uid: String,
    onSuccessListener: OnSuccessListener<DocumentSnapshot>,
    onFailureListener: OnFailureListener

) {
    val collectionRef = getCollection(AppUser.COLLECTION_NAME)
    collectionRef.document(uid).get()  //read from database
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun addRoom(
    room: Room, onSuccessListener: OnSuccessListener<Void>,
    onFailureListener: OnFailureListener
) {
    val collection = getCollection(Room.COLLECTION_NAME)
    val doc = collection.document()
    room.id = doc.id
    doc.set(room)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun getRooms(
    onSuccessListener: OnSuccessListener<QuerySnapshot>,
    onFailureListener: OnFailureListener
) {
    val collection = getCollection(Room.COLLECTION_NAME)
    collection.get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

//collection message
fun getMessageRef(roomId: String): CollectionReference {
    val collection = getCollection(Room.COLLECTION_NAME)  //room collection in firestore
    val roomRef = collection.document(roomId) //room object
    return roomRef.collection(Message.COLLECTION_NAME)
}

fun addMessage(
    message: Message,
    onSuccessListener: OnSuccessListener<Void>,
    onFailureListener: OnFailureListener
) {
    //collection message
    val messageCollRef = getMessageRef(message.roomId!!) //message collection
    val messageRef = messageCollRef.document() //document in message collection
    message.id = messageRef.id
    messageRef
        .set(message)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}