package com.yxhuang.espresso.ui

import android.view.View
import android.widget.ImageView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

/**
 * Created by yxhuang
 * Date: 2022/9/1
 * Description:
 */
object ImageViewHasDrawableMatcher {

    fun hasDrawable(): BoundedMatcher<View, ImageView> {
        return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("has drawable")
            }

            override fun matchesSafely(item: ImageView?): Boolean {
                return item?.drawable != null
            }
        }
    }
}