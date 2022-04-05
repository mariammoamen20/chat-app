package com.example.chat_app.addroom

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.chat_app.base.BaseViewModel
import com.example.chat_app.database.addRoom
import com.example.chat_app.model.Category
import com.example.chat_app.model.Room

class AddRoomViewModel : BaseViewModel<Navigator>() {
    val roomName = ObservableField<String>()
    val descrptionRoom = ObservableField<String>()

    val nameError = ObservableField<String>()
    val descriptionErro = ObservableField<String>()

    val categoryList = Category.getCategoriesList()
    var selectedCategory = categoryList[0]
    val addRoom = MutableLiveData<Boolean>()

    fun createRoom() {
        if (validate()) {
            val room = Room(
                roomName = roomName.get(),
                roomDescription = descrptionRoom.get(),
                categoryId = selectedCategory.id
            )
            showLoading.value = true
            addRoom(room, onSuccessListener = {
              showLoading.value = false
                addRoom.value = true
                //navigator back


            }, onFailureListener = {
              showLoading.value = false
                messageLiveData.value = it.localizedMessage
            })
        }
    }

    fun validate(): Boolean {
        var isValidate = true
        if (roomName.get().isNullOrBlank()) {
            nameError.set("Please! Enter Room Name ") //set error
            isValidate = false
        } else {
            nameError.set(null) //remove error
        }
        if (descrptionRoom.get().isNullOrBlank()) {
            descriptionErro.set("Please! Enter Room Description ") //set error
            isValidate = false
        } else {
            descriptionErro.set(null) //remove error
        }
        return isValidate
    }
}