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

class FragmentPop() : Fragment(), IFragment {

    private lateinit var listener: IView
    private val adapter: MusicAdapter by lazy { MusicAdapter() }
    private val presenter: Presenter = Presenter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
        view.recycler_view.adapter = adapter // Lazily initialize here...

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMeMusic()
        swipe_refresh.setOnRefreshListener {
            getMeMusic()
            swipe_refresh.isRefreshing = false // Cancel the Visual indication of a refresh
        }
    }

    override fun getMeMusic() {
        presenter.getMusic("pop") // Lazily initialize here...
    }

    override fun displayData(dataSet: List<Card>, context: Context) {
        if (context is MainActivity) listener = context // Late initialization happens here...
        adapter.dataSet = dataSet
        listener.dismissProgress()
    }

    companion object {
        @JvmStatic
        val newInstance: FragmentPop = FragmentPop()
    }
}