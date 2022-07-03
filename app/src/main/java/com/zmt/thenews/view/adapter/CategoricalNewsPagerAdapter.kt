package com.zmt.thenews.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.zmt.thenews.R
import com.zmt.thenews.view.fragment.AllNewsFragment
import com.zmt.thenews.view.fragment.TopHeadlinesFragment

class CategoricalNewsPagerAdapter(
    val activity: FragmentActivity,
    private val categoryList: List<Pair<String,String>>
) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun createFragment(position: Int): Fragment {
        return TopHeadlinesFragment.newInstance(categoryList[position].first)
    }

}