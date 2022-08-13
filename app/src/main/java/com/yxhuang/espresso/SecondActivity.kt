package com.yxhuang.espresso

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by yxhuang
 * Date: 2022/8/11
 * Description:
 */
class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.button_back).setOnClickListener {
            onBackPressed()
        }
    }
}