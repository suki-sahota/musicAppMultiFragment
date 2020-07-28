package com.example.musicapi.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: FragmentActivity, private val context: Context): FragmentStateAdapter(fa) {

//    private val TAG = "ViewPagerAdapter"

    private lateinit var listener: IView

    override fun createFragment(position: Int): Fragment {
        if (context is MainActivity) listener = context // Late initialization happens here...
        listener.showProgress()
        return when(position) {
            0 -> FragmentRock()
            1 -> FragmentClassic()
            else -> FragmentPop()
        }
    }
    override fun getItemCount(): Int = 3
}