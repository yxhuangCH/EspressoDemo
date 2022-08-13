package com.yxhuang.espresso

import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by yxhuang
 * Date: 2022/8/12
 * Description:
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class SecondActivityTest{

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(SecondActivity::class.java)

    @Test
    fun test_activity_inView() {
        onView(withId(R.id.secondary))
            .check(matches(isDisplayed()))

        // 销毁 SecondActivity
        activityScenarioRule.scenario.moveToState(Lifecycle.State.DESTROYED)
    }

    // Visibility
    @Test
    fun testVisibility_title_nextButton() {
        onView(withId(R.id.activity_secondary_title))
            .check(matches(isDisplayed()))

        onView(withId(R.id.button_back))
            .check(matches(isDisplayed()))
    }

    // Text
    @Test
    fun testTitleTextDisplayed() {
        onView(withId(R.id.activity_secondary_title))
            .check(matches(withText(R.string.text_secondaryactivity)))
    }
}