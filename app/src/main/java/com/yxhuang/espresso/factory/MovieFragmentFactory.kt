package com.yxhuang.espresso.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.yxhuang.espresso.ErrorFragment
import com.yxhuang.espresso.ui.DirectorsFragment
import com.yxhuang.espresso.ui.MovieDetailFragment
import com.yxhuang.espresso.ui.StarActorsFragment

/**
 * Created by yxhuang
 * Date: 2022/8/15
 * Description:
 */

class MovieFragmentFactory : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)
        var fragment: Fragment? = null
        var errorDescription: String? = null

        when (fragmentClass) {

            MovieDetailFragment::class.java -> {
                fragment = MovieDetailFragment()
            }

            DirectorsFragment::class.java -> {
                fragment = DirectorsFragment()
            }

            StarActorsFragment::class.java -> {
                fragment = StarActorsFragment()
            }

            else -> errorDescription = "Something went wrong."
        }

        fragment?.let { nonNullFragment ->
            return nonNullFragment
        }
        fragment = ErrorFragment(errorDescription)

        return fragment
    }


}