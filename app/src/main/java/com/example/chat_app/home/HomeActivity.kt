package com.example.chat_app.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.chat_app.R
import com.example.chat_app.addroom.AddRoomActivity
import com.example.chat_app.addroom.AddRoomAdapter
import com.example.chat_app.base.BaseActivity
import com.example.chat_app.database.getRooms
import com.example.chat_app.databinding.ActivityHomeBinding
import com.example.chat_app.model.Room

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), Navigator {
    val addRoomAdapter = AddRoomAdapter(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view_data_binding.vm = view_model
        view_model.navigator = this

        view_data_binding.roomRecyclerView.adapter = addRoomAdapter
    }

    override fun onStart() {
        super.onStart()
        getRooms(onFailureListener = {
            Toast.makeText(this, "Can't Fetch Room", Toast.LENGTH_SHORT).show()
        },
            onSuccessListener = {
                val room = it.toObjects(Room::class.java) //add rooms
                addRoomAdapter.changeData(room)
//                val roomList = mutableListOf<Room?>()
//                it.documents.forEach {
//                    val room = it.toObject(Room::class.java)
//                    roomList.add(room)
//                }
            }
        )
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun goToAddRoom() {
        val intent = Intent(this, AddRoomActivity::class.java)
        startActivity(intent)
    }
}