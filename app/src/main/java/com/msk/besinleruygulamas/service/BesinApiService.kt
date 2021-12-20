package com.msk.besinleruygulamas.service

import com.msk.besinleruygulamas.model.besin
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class BesinApiService {

    private val BASE_URL="https://github.com/atilsamancioglu/"
    private val api=Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(BesinApi::class.java)
    fun getData():Single<List<besin>>{
        return api.getBesin()
    }
}