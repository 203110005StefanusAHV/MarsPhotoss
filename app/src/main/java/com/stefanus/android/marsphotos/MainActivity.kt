package com.stefanus.android.marsphotos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.stefanus.android.marsphotos.R

/**
 * MainActivity untuk mengatur tampilan pada activity_main, tempat fragmen yang berisi
 * overviewFragment.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}