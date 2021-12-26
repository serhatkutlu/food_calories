package com.msk.besinleruygulamas.model

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.msk.besinleruygulamas.service.BesinApiService
import com.msk.besinleruygulamas.service.BesinDatabase
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class besin_listesi_viewmodel(application: Application):BaseViewModel(application) {
    val besinler = MutableLiveData<List<besin>>()
    val hata_mesaji = MutableLiveData<Boolean>()
    val progress_bar = MutableLiveData<Boolean>()

    private val guncelleme_zamanı=0.5*60*1000*1000*1000L

    private val besin_api_service=BesinApiService()
    private val disposable=CompositeDisposable()

    private val ozelSharedPreferences=com.msk.besinleruygulamas.util.ozelSharedPreferences(getApplication())

fun refreshdata(){

    val kaydedilme_zamanı=ozelSharedPreferences.zamani_al()
    if (kaydedilme_zamanı != null&&kaydedilme_zamanı!=0L&&System.nanoTime()-kaydedilme_zamanı<guncelleme_zamanı){
        Besinleri_SQlitedan_Al()
    }
    else{
        Besinleri_internetten_al()
    }

}
    fun refresh_data_from_internet(){
        Besinleri_internetten_al()
    }
    private fun Besinleri_SQlitedan_Al(){
        launch {
            val besin_listesi=BesinDatabase(getApplication()).BesinDAO().getAllBesin()
            besinleri_goster(besin_listesi)
            Toast.makeText(getApplication(),"room",Toast.LENGTH_LONG).show()
        }


    }
     private fun Besinleri_internetten_al(){
        progress_bar.value=true

        disposable.add(
            besin_api_service.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<besin>>(){
                    override fun onSuccess(t: List<besin>) {
                       sqlite_sakla(t)
                        Toast.makeText(getApplication(),"internet",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {

                        hata_mesaji.value=true
                        progress_bar.value=false
                        e.printStackTrace()

                    }

                }
                )



        )

     }
private fun besinleri_goster(t:List<besin>){
    besinler.value=t
    hata_mesaji.value=false
    progress_bar.value=false

}
    private fun sqlite_sakla(besinListesi:List<besin>){
        launch {
            val dao= BesinDatabase(getApplication()).BesinDAO()
            dao.deleteAllBesin()
            val id=dao.insert_all(*besinListesi.toTypedArray())
            var i=0
            while (i<besinListesi.size){
                besinListesi[i].id=id[i].toInt()
                i=i+1

            }
            besinleri_goster(besinListesi)

        }
        ozelSharedPreferences.ZamanKaydet(System.nanoTime())
    }
}