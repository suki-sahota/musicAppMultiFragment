package com.example.musicapi.view

import com.example.musicapi.model.Card

interface IView {

    fun bindPresenter()
    fun swipeAdapter()
    fun onBackPressed()
    fun showToast(message: String)
    fun showProgress()
    fun dismissProgress()
    fun getMusic(genre: String)
    fun displayRockData(dataSet: List<Card>, fragment: FragmentRock)
    fun displayClassicData(dataSet: List<Card>, fragment: FragmentClassic)
    fun displayPopData(dataSet: List<Card>, fragment: FragmentPop)
}