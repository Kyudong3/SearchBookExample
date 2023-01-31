package com.kyudong3.searchbookexample.ui.activity

import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.kyudong3.searchbookexample.R
import com.kyudong3.searchbookexample.base.BaseActivity
import com.kyudong3.searchbookexample.databinding.ActivityMainBinding
import com.kyudong3.searchbookexample.ui.widget.recyclerview.SearchBookPagerAdapter
import com.kyudong3.searchbookexample.utils.provider.ResourceProvider
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    @Inject
    lateinit var resourceProvider: ResourceProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.executePendingBindings()

        binding.viewPager.adapter = SearchBookPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                TAB_SEARCH_BOOK -> tab.text = resourceProvider.getString(R.string.search)
                else -> tab.text = resourceProvider.getString(R.string.bookmark)
            }
        }.attach()
    }

    companion object {
        private const val TAB_SEARCH_BOOK = 0
    }
}
