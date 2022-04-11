package com.example.chat_app.chat

import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat_app.base.BaseViewModel
import com.example.chat_app.database.addMessage
import com.example.chat_app.model.AppUser
import com.example.chat_app.model.Message
import com.example.chat_app.model.Room
import com.example.chat_app.objects.DataUtils
import java.util.*

class ChatViewModel : BaseViewModel<Navigator>() {
    var messageFiled = ObservableField<String>()
    val toastLiveData = MutableLiveData<String>()
    var room:Room?=null
    fun sendMessage(){
      val message = Message(
          content = messageFiled.get(),
          roomId = room?.id,
          senderId = DataUtils.user?.id,
          senderName = DataUtils.user?.userName,
          dateTime = Date().time  //time im milly second
      )
        addMessage(message,
            onSuccessListener = {
             messageFiled.set(" ")
        },onFailureListener = {
              toastLiveData.value = "Something Went Wrong , Try Again Later ! "
        })
        //save message in firebase
    }
}