package com.yxhuang.espresso.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.yxhuang.espresso.R
import com.yxhuang.espresso.factory.MovieFragmentFactory

class MainActivity : AppCompatActivity() {
    // dependency (typically would be injected with dagger)
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = MovieFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }


    private fun init(){
        if(supportFragmentManager.fragments.size == 0){
            val movieId = 1
            val bundle = Bundle()
            bundle.putInt("movie_id", movieId)
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailFragment::class.java, bundle)
                .commit()
        }
    }

//    private fun initGlideInstance(){
//        val requestOptions = RequestOptions
//            .placeholderOf(R.drawable.default_image)
//            .error(R.drawable.default_image)
//        requestManager = Glide.with(application)
//            .setDefaultRequestOptions(requestOptions)
//    }

}