package com.yxhuang.espresso.ui

import android.os.IBinder
import android.view.WindowManager
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

/**
 * Created by yxhuang
 * Date: 2022/9/25
 * Description:
 *
 * Author: http://www.qaautomated.com/2016/01/how-to-test-toast-message-using-espresso.html
*/
class ToastMatcher : TypeSafeMatcher<Root?>() {

    override fun describeTo(description: Description?) {
        description?.appendText("There is toast")
    }

    override fun matchesSafely(item: Root?): Boolean {
        val type: Int?= item?.windowLayoutParams?.get()?.type
        if (type == WindowManager.LayoutParams.TYPE_TOAST){
            val windowToken: IBinder = item.decorView.windowToken
            val appToken: IBinder = item.decorView.applicationWindowToken
            if (windowToken === appToken){
                return true
            }
        }
        return false
    }
}