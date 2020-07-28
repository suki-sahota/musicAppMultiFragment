package com.example.musicapi.presenter

import com.example.musicapi.model.MusicModel
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface IMusicApi {
    @GET("search")
    fun getMusic(@Query("term") type: String,
                 @Query("media") media: String = "music",
                 @Query("entity") entity: String = "song",
                 @Query("limit") size: Int = 50): Observable<MusicModel>

    companion object {
        fun getMusicApi(): IMusicApi {
            return Retrofit.Builder()
                .baseUrl("https://itunes.apple.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(IMusicApi::class.java)
        }
    }
}