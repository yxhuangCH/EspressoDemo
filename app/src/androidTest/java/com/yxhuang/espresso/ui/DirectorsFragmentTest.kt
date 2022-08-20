package com.yxhuang.espresso.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.yxhuang.espresso.R
import com.yxhuang.espresso.factory.MovieFragmentFactory
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by yxhuang
 * Date: 2022/8/18
 * Description:
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class DirectorsFragmentTest{

    @Test
    fun test_isDirectorsListVisible() {
        // GIVEN
        val directors = arrayListOf("贾樟柯", "张艺谋")
        val fragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putStringArrayList("args_directors", directors)
        val scenario = launchFragmentInContainer<DirectorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFactory
        )

        // 验证
        onView(withId(R.id.directors_text))
            .check(matches(withText(DirectorsFragment.stringBuilderForDirectors(directors))))
    }
}