package com.kyudong3.searchbookexample.ui.widget.recyclerview

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kyudong3.searchbookexample.ui.fragment.BookmarkFragment
import com.kyudong3.searchbookexample.ui.fragment.SearchBookFragment


class SearchBookPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            FRAGMENT_SEARCH_BOOK -> SearchBookFragment.newInstance()
            else -> BookmarkFragment.newInstance()
        }

    companion object {
        private const val FRAGMENT_SEARCH_BOOK = 0
        private const val FRAGMENT_BOOKMARK = 1
    }
}
