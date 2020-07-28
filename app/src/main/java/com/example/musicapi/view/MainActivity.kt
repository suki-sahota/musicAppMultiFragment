package com.example.musicapi.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.musicapi.R
import com.example.musicapi.model.Card
import com.example.musicapi.presenter.Presenter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), IView {

//    private val TAG = "MainActivity"

    private val presenter: Presenter = Presenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentRock.presenter = presenter
        FragmentClassic.presenter = presenter
        FragmentPop.presenter = presenter
        showProgress()
        bindPresenter()
        swipeAdapter()
    }

    override fun bindPresenter() {
        presenter.onBind(this)
    }

    override fun swipeAdapter() {
        pager.adapter = ViewPagerAdapter(this, this)

        TabLayoutMediator(tab_layout, pager,
            TabLayoutMediator.TabConfigurationStrategy
            { tab, position ->
                when (position){
                    ViewPagerAdapter.FragmentType.Rock.ordinal -> {
                    tab.icon = getDrawable(R.drawable.ic_rock_24)
                    tab.text = getString(R.string.tab_rock)
                }
                    ViewPagerAdapter.FragmentType.Classic.ordinal -> {
                    tab.icon = getDrawable(R.drawable.ic_classic_24)
                    tab.text = getString(R.string.tab_classic)
                }
                    ViewPagerAdapter.FragmentType.Pop.ordinal -> {
                    tab.icon = getDrawable(R.drawable.ic_pop_24)
                    tab.text = getString(R.string.tab_pop)
                }
            }
            }).attach()
    }

    override fun onBackPressed() {
        if (pager.currentItem == 0) {
            super.onBackPressed()
        } else {
            pager.currentItem = pager.currentItem - 1
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun showProgress() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun dismissProgress() {
        progress_bar.visibility = View.GONE
    }

    override fun getMusic(genre: String) {
        presenter.getMusic(genre)
    }

    override fun displayRockData(dataSet: List<Card>, fragment: FragmentRock) {
        fragment.displayData(dataSet, this)
    }

    override fun displayClassicData(dataSet: List<Card>, fragment: FragmentClassic) {
        fragment.displayData(dataSet, this)
    }

    override fun displayPopData(dataSet: List<Card>, fragment: FragmentPop) {
        fragment.displayData(dataSet, this)
    }
}