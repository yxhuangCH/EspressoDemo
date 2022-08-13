package com.yxhuang.espresso

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by yxhuang
 * Date: 2022/8/11
 * Description:
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{

    @Test
    fun test_activity_inView() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun testVisibility_title_nextButton() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        // 方法 1
        onView(withId(R.id.activity_main_title))
            .check(matches(isDisplayed()))

        // 方法 2
        onView(withId(R.id.activity_main_title))
            .check(matches(withEffectiveVisibility(Visibility.VISIBLE)))

        onView(withId(R.id.button_next_activity))
            .check(matches(isDisplayed()))
    }

    @Test
    fun testTitleTextDisplayed() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.activity_main_title))
            .check(matches(withText(R.string.text_mainactivity)))

    }
}