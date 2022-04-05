package com.example.chat_app.model

import com.example.chat_app.R

data class Category(
    val id: String? = null,
    val name: String? = null,
    val imageId: Int? = null
) {
    companion object {
        const val MUSIC = "music"
        const val SPORT = "sport"
        const val MOVIES = "movies"
        fun fromId(catId: String): Category {
            when (catId) {
                MUSIC -> {
                    return Category(
                        MUSIC,
                        name = "music",
                        imageId = R.drawable.movies
                    )
                }
                SPORT -> {
                    return Category(
                        SPORT,
                        name = "sport",
                        imageId = R.drawable.movies
                    )
                }
                else -> {
                    return Category(
                        MOVIES,
                        name = "movies",
                        imageId = R.drawable.movies
                    )

                }
            }
        }
        fun getCategoriesList():List<Category>{
            return listOf(
                fromId(MUSIC),
                fromId(SPORT),
                fromId(MOVIES)
            )
        }
    }
}