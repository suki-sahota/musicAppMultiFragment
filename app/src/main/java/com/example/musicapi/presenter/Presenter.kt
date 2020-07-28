package com.example.musicapi.presenter

import com.example.musicapi.view.FragmentClassic
import com.example.musicapi.view.FragmentPop
import com.example.musicapi.view.FragmentRock
import com.example.musicapi.view.IView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class Presenter() {

    fun onBind(view: IView) {
        Presenter.view = view // Lately initialize here...
    }

    fun getMusic(genre: String) {
        IMusicApi.getMusicApi().getMusic(genre) // Observable<MusicModel>
            .subscribeOn(Schedulers.io()) // Subscribe to worker thread
            .observeOn(AndroidSchedulers.mainThread()) // Observer
            .subscribe {
                when(genre) { // Combine these three at the end with one function in main
                    "rock" -> view.displayData(it.results, FragmentRock.newInstance)
                    "classick" -> view.displayData(it.results, FragmentClassic.newInstance)
                    else -> view.displayData(it.results, FragmentPop.newInstance)
                }
            }
    }

    companion object {
        private lateinit var view: IView
    }
}