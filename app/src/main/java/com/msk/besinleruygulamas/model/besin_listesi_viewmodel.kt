package com.msk.besinleruygulamas.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class besin_listesi_viewmodel: ViewModel() {
    val besinler = MutableLiveData<List<besin>>()
    val hata_mesaji = MutableLiveData<Boolean>()
    val progress_bar = MutableLiveData<Boolean>()


}