package com.example.musicapi.presenter

import com.example.musicapi.view.FragmentClassic
import com.example.musicapi.view.FragmentPop
import com.example.musicapi.view.FragmentRock
import com.example.musicapi.view.IView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

//class Presenter(val view: ViewInterface) { // SOLID design principles (avoid hot fixes!)
class Presenter {

//    private val TAG = "Presenter"

    private lateinit var view: IView
    companion object {
        lateinit var fragmentRock: FragmentRock
        lateinit var fragmentClassic: FragmentClassic
        lateinit var fragmentPop: FragmentPop
    }

    fun onBind(view: IView) {
        this.view = view // Late initialization happens here...
    }

    fun getMusic(genre: String) {
        IMusicApi.getMusicApi().getMusic(genre) // Observable<MusicModel>
            .subscribeOn(Schedulers.io()) // Subscribe to worker thread
            .observeOn(AndroidSchedulers.mainThread()) // Observer
            .subscribe {
                when(genre) {
                    "rock" -> view.displayRockData(it.results, fragmentRock)
                    "classick" -> view.displayClassicData(it.results, fragmentClassic)
                    else -> view.displayPopData(it.results, fragmentPop)
                }
            }
    }
}