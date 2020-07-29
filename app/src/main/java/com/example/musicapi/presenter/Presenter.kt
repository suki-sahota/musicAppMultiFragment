package com.example.musicapi.presenter

import com.example.musicapi.view.FragmentClassic
import com.example.musicapi.view.FragmentPop
import com.example.musicapi.view.FragmentRock
import com.example.musicapi.view.IView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class Presenter() {

    fun onBind(iView: IView) {
        Presenter.iView = iView // Lately initialize here...
    }

    fun getMusic(genre: String) {
        IMusicApi.getMusicApi().getMusic(genre) // Observable<MusicModel>
            .subscribeOn(Schedulers.io()) // Subscribe on worker thread
            .observeOn(AndroidSchedulers.mainThread()) // Observer on main thread
            .subscribe {
                when(genre) {
                    "rock" -> iView.displayData(it.results, FragmentRock.newInstance)
                    "classick" -> iView.displayData(it.results, FragmentClassic.newInstance)
                    else -> iView.displayData(it.results, FragmentPop.newInstance)
                }
            }
    }

    companion object {
        private lateinit var iView: IView
    }
}