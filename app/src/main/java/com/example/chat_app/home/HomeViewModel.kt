package com.example.chat_app.home

import com.example.chat_app.base.BaseViewModel

class HomeViewModel : BaseViewModel<Navigator>() {
    fun createRoom(){
        navigator?.goToAddRoom()
    }
}