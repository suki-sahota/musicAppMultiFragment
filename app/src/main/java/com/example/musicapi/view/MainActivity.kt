package com.example.musicapi.view

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
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

    private val presenter: Presenter by lazy { Presenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showProgress()
        bindPresenter() // Important step to bind Presenter
        swipeAdapter()
    }

    override fun bindPresenter() {
        presenter.onBind(this) // Lazily initialize here...
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

    override fun displayData(dataSet: List<Card>, frag: IFragment) {
        frag.displayData(dataSet, this)
    }

    override fun playAudio(item: Card) {
        // Create implicit intent
        val audioIntent = Intent(ACTION_VIEW).apply {
            setDataAndType(Uri.parse(item.previewUrl), "audio/*")
        }

        // Send implicit intent to system
        startActivity(audioIntent)

    }
}