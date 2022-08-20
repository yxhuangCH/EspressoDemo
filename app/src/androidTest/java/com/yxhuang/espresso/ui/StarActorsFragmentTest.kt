package com.yxhuang.espresso.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.yxhuang.espresso.R
import com.yxhuang.espresso.factory.MovieFragmentFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.internal.matchers.Matches

/**
 * Created by yxhuang
 * Date: 2022/8/20
 * Description:
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class StarActorsFragmentTest{

    @Test
    fun test_isActorsListVisible() {
        // GIVEN
        val actors = arrayListOf(
            "李雪健",
            "吴京",
            "郝蕾"
        )
        val fragmentFragmentFactory = MovieFragmentFactory()
        val bundle = Bundle()
        bundle.putStringArrayList("args_actors", actors)
        val scenario = launchFragmentInContainer<StarActorsFragment>(
            fragmentArgs = bundle,
            factory = fragmentFragmentFactory
        )

        // VERIFY
        onView(withId(R.id.star_actors_text))
            .check(ViewAssertions.matches(withText(StarActorsFragment.stringBuilderForStarActors(actors))))

    }
}