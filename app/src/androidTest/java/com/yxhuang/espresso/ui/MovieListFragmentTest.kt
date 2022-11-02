package com.yxhuang.espresso.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.yxhuang.espresso.R
import com.yxhuang.espresso.data.FakeMovieData
import com.yxhuang.espresso.ui.util.EspressoIdlingResourceRule
import com.yxhuang.espresso.util.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by yxhuang
 * Date: 2022/10/15
 * Description:
 */

@RunWith(AndroidJUnit4ClassRunner::class)
class MovieListFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    val LIST_ITEM_IN_TEST = 4
    val MOVIE_IN_TEST = FakeMovieData.movies[LIST_ITEM_IN_TEST]

    @get:Rule
    val espressoIdlingResourceRule = EspressoIdlingResourceRule()

//    @Before
//    fun registerIdlingResource() {
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
//    }
//
//    @After
//    fun unregisterIdlingResource() {
//        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
//    }

    @Test
    fun test_isListFragmentVisible_onAppLaunch() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_itemText_Exist() {
        val  movieTitle = MOVIE_IN_TEST.title
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.scrollTo<MoviesListAdapter.MovieViewHolder>(hasDescendant(withText(movieTitle))))
    }

    @Test
    fun test_selectListItem_isDetailFragmentVisible() {
        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view)).perform(
            actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(LIST_ITEM_IN_TEST, click()))
        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))
    }

    @Test
    fun test_backNavigation_toMovieListFragment() {
        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view)).perform(
            actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(LIST_ITEM_IN_TEST, click()))
        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        pressBack()

        // Confirm MovieListFragment in view
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

    @Test
    fun test_navDirectorsFragment_validateDirectorsList() {
        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view)).perform(
            actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        // Nav to DirectorsFragment
        onView(withId(R.id.movie_directiors)).perform(click())

        // Confirm correct directors are visible
        onView(withId(R.id.directors_text))
            .check(matches(withText(
                DirectorsFragment.stringBuilderForDirectors(MOVIE_IN_TEST.directors!!)
            )))
    }



    @Test
    fun test_navStarActorsFragment_validateActorsList() {
        // Click list item #LIST_ITEM_IN_TEST
        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MoviesListAdapter.MovieViewHolder>(LIST_ITEM_IN_TEST, click()))

        // Confirm nav to DetailFragment and display title
        onView(withId(R.id.movie_title)).check(matches(withText(MOVIE_IN_TEST.title)))

        // Nav to DirectorsFragment
        onView(withId(R.id.movie_star_actors)).perform(click())

        // Confirm correct directors are visible
        onView(withId(R.id.star_actors_text))
            .check(matches(withText(
                StarActorsFragment.stringBuilderForStarActors(MOVIE_IN_TEST.star_actors!!)
            )))
    }
}


