package com.yxhuang.espresso.ui

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.Instrumentation
import android.content.ContentResolver
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasData
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.yxhuang.espresso.R
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by yxhuang
 * Date: 2022/8/29
 * Description:
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class ImageViewerActivityTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(ImageViewerActivity::class.java)

    @Test
    fun test_validateIntentSendToPackage() {

        // GIVEN
        val expectedIntent: Matcher<Intent> = hasAction(Intent.ACTION_PICK)

        val activityResult = createGalleryPickActivityResultStub()
        intending(expectedIntent).respondWith(activityResult)

        // Execute and Verify
        onView(withId(R.id.image)).check(matches(not(ImageViewHasDrawableMatcher.hasDrawable())))
        onView(withId(R.id.button_launch_camera)).perform(click())
        // 验证
        intended(expectedIntent)
        onView(withId(R.id.image)).check(matches(ImageViewHasDrawableMatcher.hasDrawable()))
    }

    private fun createGalleryPickActivityResultStub(): Instrumentation.ActivityResult {
        val bundle = Bundle()
        bundle.putParcelable(
            KEY_IMAGE_DATA, BitmapFactory.decodeResource(
                intentsTestRule.activity.resources,
                R.drawable.ic_launcher_background
            )
        )
        val resultData = Intent()
        resultData.putExtras(bundle)
        return Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
    }
}