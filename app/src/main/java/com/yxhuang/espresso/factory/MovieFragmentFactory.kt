package com.yxhuang.espresso.factory

import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.request.RequestOptions
import com.yxhuang.espresso.data.source.MoviesDataSource
import com.yxhuang.espresso.ui.DirectorsFragment
import com.yxhuang.espresso.ui.MovieDetailFragment
import com.yxhuang.espresso.ui.MovieListFragment
import com.yxhuang.espresso.ui.StarActorsFragment

/**
 * Created by yxhuang
 * Date: 2022/8/15
 * Description:
 */


class MovieFragmentFactory(
        private val requestOptions: RequestOptions? = null,
        private val moviesDataSource: MoviesDataSource? = null
) : FragmentFactory(){

    private val TAG: String = "AppDebug"

    override fun instantiate(classLoader: ClassLoader, className: String) =

        when(className){

            MovieListFragment::class.java.name -> {
                if (moviesDataSource != null) {
                    MovieListFragment(moviesDataSource)
                } else {
                    super.instantiate(classLoader, className)
                }
            }

            MovieDetailFragment::class.java.name -> {
                if(requestOptions != null
                    && moviesDataSource != null){
                    MovieDetailFragment(
                        requestOptions,
                        moviesDataSource
                    )
                }
                else{
                    super.instantiate(classLoader, className)
                }
            }

            DirectorsFragment::class.java.name -> {
                DirectorsFragment()
            }

            StarActorsFragment::class.java.name -> {
                StarActorsFragment()
            }

            else -> {
                super.instantiate(classLoader, className)
            }
        }

}