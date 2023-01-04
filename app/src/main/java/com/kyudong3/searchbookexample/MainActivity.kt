package com.kyudong3.searchbookexample

import android.os.Bundle
import com.kyudong3.searchbookexample.base.BaseActivity
import com.kyudong3.searchbookexample.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
