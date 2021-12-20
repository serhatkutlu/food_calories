package com.msk.besinleruygulamas.service

import com.msk.besinleruygulamas.model.besin
import io.reactivex.Single
import retrofit2.http.GET

interface BesinApi {

    @GET("https://github.com/atilsamancioglu/BTK20-JSONVeriSeti/blob/master/besinler.json")
    fun getBesin():Single<List<besin>>
}