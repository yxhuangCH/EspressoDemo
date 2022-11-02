package com.yxhuang.espresso.ui.util

import androidx.test.espresso.IdlingRegistry
import com.yxhuang.espresso.util.EspressoIdlingResource
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Created by yxhuang
 * Date: 2022/11/1
 * Description:
 */
class EspressoIdlingResourceRule : TestWatcher() {

    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        super.starting(description)
    }

    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        super.finished(description)
    }

}