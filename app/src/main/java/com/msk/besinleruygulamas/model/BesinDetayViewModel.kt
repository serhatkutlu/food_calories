package com.msk.besinleruygulamas.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BesinDetayViewModel: ViewModel() {
    var besin_LiveData= MutableLiveData<besin>()

    fun  GetRoomData(){
        val muz=besin("muz","10","25","63","25","null")
        besin_LiveData.value=muz
    }




}