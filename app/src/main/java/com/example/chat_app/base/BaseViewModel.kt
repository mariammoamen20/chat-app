package com.example.chat_app.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<N> : ViewModel() {
    val messageLiveData = MutableLiveData<String>()
    val showLoading = MutableLiveData<Boolean>()
    var navigator : N?=null
}