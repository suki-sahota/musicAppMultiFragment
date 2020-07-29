package com.example.musicapi.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fa: FragmentActivity, private val context: Context): FragmentStateAdapter(fa) {

    private lateinit var iView: IView

    enum class FragmentType {
        Rock, Classic, Pop
    }

    override fun createFragment(position: Int): Fragment {
        if (context is MainActivity) iView = context // Lately initialize here...
        iView.showProgress()
        return when(position) {
            FragmentType.Rock.ordinal -> FragmentRock.newInstance
            FragmentType.Classic.ordinal -> FragmentClassic.newInstance
            FragmentType.Pop.ordinal -> FragmentPop.newInstance
            else -> FragmentRock.newInstance
        }
    }
    override fun getItemCount(): Int = 3
}