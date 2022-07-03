package com.zmt.thenews.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zmt.thenews.view.fragment.AllNewsFragment
import com.zmt.thenews.view.fragment.TopHeadlinesFragment

class NewsPagerAdapter(activity: FragmentActivity) :
    FragmentStateAdapter(activity) {
    companion object {
        private const val TAB_COUNT = 2
    }

    override fun getItemCount(): Int {
        return TAB_COUNT
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllNewsFragment.newInstance()
            1 -> AllNewsFragment.newInstance()
            else -> throw Exception("fragment not found")
        }
    }

}