package com.kyudong3.searchbookexample.ui.activity

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.kyudong3.searchbookexample.R
import com.kyudong3.searchbookexample.base.BaseActivity
import com.kyudong3.searchbookexample.databinding.ActivityMainBinding
import com.kyudong3.searchbookexample.ui.widget.recyclerview.SearchBookPagerAdapter


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.executePendingBindings()

        binding.viewPager.adapter = SearchBookPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "검색"
                else -> tab.text = "즐겨찾기"
            }
        }.attach()
    }
}
