package com.example.chat_app.chat

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat_app.objects.Constant
import com.example.chat_app.R
import com.example.chat_app.base.BaseActivity
import com.example.chat_app.database.getMessageRef
import com.example.chat_app.databinding.ActivityChatBinding
import com.example.chat_app.model.Message
import com.example.chat_app.model.Room
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.Query

class ChatActivity : BaseActivity<ActivityChatBinding , ChatViewModel>(),Navigator {
    lateinit var room : Room
    val messageAdapter = MessageAdapter()
    lateinit var layoutManger : LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view_data_binding.vm = view_model
        room = intent.getParcelableExtra(Constant.EXTRA_ROOM)!!
        view_model.room = room
        layoutManger = LinearLayoutManager(this)
        layoutManger.stackFromEnd = true  //message will apear from bottom to top
        view_data_binding.messageRecyclerview.layoutManager = layoutManger
        view_data_binding.messageRecyclerview.adapter=messageAdapter
        listenForMessagesUpdate()

    }
    //listen to realtime updates
   fun listenForMessagesUpdate(){
       getMessageRef(room.id!!)
           .orderBy("dateTime" , Query.Direction.ASCENDING)
           .addSnapshotListener{ snapshots, exception ->
         if(exception !=null){
             Toast.makeText(this,"Can't Retrieve Message",Toast.LENGTH_SHORT).show()
         }
         else {
             val newMessageList = mutableListOf<Message>()
             for (dc in snapshots!!.documentChanges) {
                 when (dc.type) {
                     DocumentChange.Type.ADDED -> {
                      val message = dc.document.toObject(Message::class.java)
                         newMessageList.add(message)

                     }
                 }
             }
             messageAdapter.appendMessage(newMessageList)
             view_data_binding.messageRecyclerview.smoothScrollToPosition(messageAdapter.itemCount)
         }
       }
   }
    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }

    override fun initViewModel(): ChatViewModel {
        return ViewModelProvider(this).get(ChatViewModel::class.java)
    }
}