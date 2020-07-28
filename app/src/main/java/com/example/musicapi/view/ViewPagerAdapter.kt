package com.example.musicapi.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: FragmentActivity, private val context: Context): FragmentStateAdapter(fa) {

//    private val TAG = "ViewPagerAdapter"

    private lateinit var listener: IView

    enum class FragmentType {
        Rock, Classic, Pop
    }

    override fun createFragment(position: Int): Fragment {
        if (context is MainActivity) listener = context // Late initialization happens here...
        listener.showProgress()
        return when(position) {
            FragmentType.Rock.ordinal -> FragmentRock()
            FragmentType.Classic.ordinal -> FragmentClassic()
            FragmentType.Pop.ordinal -> FragmentPop()
            else -> FragmentRock()
        }
    }
    override fun getItemCount(): Int = 3
}