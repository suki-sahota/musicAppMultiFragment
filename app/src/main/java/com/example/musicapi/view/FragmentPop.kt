package com.example.musicapi.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.musicapi.R
import com.example.musicapi.model.Card
import com.example.musicapi.presenter.Presenter
import kotlinx.android.synthetic.main.fragment_recycler_view.*
import kotlinx.android.synthetic.main.fragment_recycler_view.view.*

class FragmentPop() : Fragment() {

//    private val TAG = "FragmentPop"

    private lateinit var listener: IView
    private val adapter: MusicAdapter by lazy { MusicAdapter() }
    companion object {
        lateinit var presenter: Presenter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Ensures fragment is implemented in an activity that inherits IView
        if (context is MainActivity) listener = context // Late initialization happens here...
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(
            R.layout.fragment_recycler_view, container, false)

        view.recycler_view.layoutManager = LinearLayoutManager(activity)
        view.recycler_view.adapter = adapter // Constructor in lazy block is called...

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Presenter.fragmentPop = this
        getMeMusic()
        swipe_refresh.setOnRefreshListener {
            getMeMusic()
            swipe_refresh.isRefreshing = false // Cancel the Visual indication of a refresh
        }
    }

    private fun getMeMusic() {
        presenter.getMusic("pop")
    }

    fun displayData(dataSet: List<Card>, context: Context) {
        if (context is MainActivity) listener = context // Late initialization happens here...
        adapter.dataSet = dataSet
        listener.dismissProgress()
    }
}