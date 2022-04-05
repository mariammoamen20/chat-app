package com.example.chat_app.model

data class Room(
    var id: String? = null,
    val roomName: String? = null,
    val roomDescription: String? = null,
    val categoryId: String? = null,
) {
    fun getCategoryImage(): Int? {
        return Category.fromId(categoryId ?: Category.SPORT).imageId
    }

    companion object {
        const val COLLECTION_NAME = "Rooms" //collection name in fierstore
    }
}