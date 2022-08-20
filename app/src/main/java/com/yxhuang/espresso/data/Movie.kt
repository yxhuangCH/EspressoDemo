package com.yxhuang.espresso.data

/**
 * Created by yxhuang
 * Date: 2022/8/15
 * Description:
 */
data class Movie(
    val id: Int,
    val title: String,
    val image: String,
    val description: String,
    val directors: ArrayList<String>?,
    val star_actors: ArrayList<String>?
)
