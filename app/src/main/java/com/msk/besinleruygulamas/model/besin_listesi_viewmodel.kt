package com.msk.besinleruygulamas.model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msk.besinleruygulamas.service.BesinApiService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class besin_listesi_viewmodel: ViewModel() {
    val besinler = MutableLiveData<List<besin>>()
    val hata_mesaji = MutableLiveData<Boolean>()
    val progress_bar = MutableLiveData<Boolean>()

    private val besin_api_service=BesinApiService()
    private val disposable=CompositeDisposable()

fun refreshdata(){
    Besinleri_internetten_al()
}
     fun Besinleri_internetten_al(){
        progress_bar.value=true

        disposable.add(
            besin_api_service.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<besin>>(){
                    override fun onSuccess(t: List<besin>) {
                        besinler.value=t
                        hata_mesaji.value=false
                        progress_bar.value=false

                    }

                    override fun onError(e: Throwable) {

                        hata_mesaji.value=true
                        progress_bar.value=false
                        e.printStackTrace()

                    }

                })
        )

     }

}