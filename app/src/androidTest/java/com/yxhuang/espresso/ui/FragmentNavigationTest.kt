package com.yxhuang.espresso.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.yxhuang.espresso.R
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by yxhuang
 * Date: 2022/8/20
 * Description:
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class FragmentNavigationTest {

    @Test
    fun testMovieFragmentsNavigation() {

        // Setup
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // NAV DirectorsFragment
        onView(withId(R.id.movie_directiors)).perform(click())

        // 验证
        onView(withId(R.id.fragment_directors_parent)).check(matches(isDisplayed()))

        // 返回 MovieDetailFragment
        pressBack()

        // 验证 MovieDetailFragment 页面
        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed()))

        // 跳转到 StarActorsFragment
        onView(withId(R.id.movie_star_actors)).perform(click())

        // 验证 StarActorsFragment
        onView(withId(R.id.fragment_star_actors_parent)).check(matches(isDisplayed()))

        // 返回 MovieDetailFragment
        pressBack()

        // 验证 MovieDetailFragment 页面
        onView(withId(R.id.fragment_movie_detail_parent)).check(matches(isDisplayed()))

    }
}