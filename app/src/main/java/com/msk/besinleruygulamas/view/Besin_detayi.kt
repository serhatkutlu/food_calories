package com.msk.besinleruygulamas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.msk.besinleruygulamas.R
import com.msk.besinleruygulamas.model.BesinDetayViewModel
import kotlinx.android.synthetic.main.fragment_besin_detayi.*
import kotlinx.android.synthetic.main.recycler_row.*

class besin_detayi : Fragment() {
lateinit var viewmodel:BesinDetayViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_detayi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel= ViewModelProviders.of(this).get(BesinDetayViewModel::class.java)
        viewmodel.GetRoomData()
    }
    fun ObserveLiveData(){
        viewmodel.besin_LiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                besin_ismi.text=it.besin_isim
                besin_kalorisi.text=it.besin_kalori
                detay_besin_karbonhidratı.text=it.besin_karbonhidrat
                detay_besin_yag.text=it.besin_yagi
                detay_besin_protein.text=it.besin_protein
            }
        })
    }


}
