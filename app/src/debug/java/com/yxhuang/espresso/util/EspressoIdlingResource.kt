package com.yxhuang.espresso.util

import androidx.test.espresso.idling.CountingIdlingResource

/**
 * Created by yxhuang
 * Date: 2022/10/24
 * Description:
 */
object EspressoIdlingResource {

    private const val RESOURCE = "GLOBAL"

    @JvmField
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        if (!countingIdlingResource.isIdleNow){
            countingIdlingResource.decrement()
        }
    }

}