package com.yxhuang.espresso.data.source

import com.yxhuang.espresso.data.Movie

/**
 * Created by yxhuang
 * Date: 2022/8/15
 * Description:
 */
interface MoviesDataSource {

    fun getMovie(movieId: Int): Movie?
}