package com.example.musicapi.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.musicapi.model.Card

interface IFragment {
    fun onAttach(context: Context)
    fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    fun onViewCreated(view: View, savedInstanceState: Bundle?)
    fun getMeMusic()
    fun displayData(dataSet: List<Card>, context: Context)

}