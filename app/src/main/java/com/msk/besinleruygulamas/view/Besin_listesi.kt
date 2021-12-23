package com.msk.besinleruygulamas.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.lifecycle.Observer

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.msk.besinleruygulamas.R
import com.msk.besinleruygulamas.adapter.recycler_adapter
import com.msk.besinleruygulamas.model.besin_listesi_viewmodel
import kotlinx.android.synthetic.main.fragment_besin_listesi.*


class besin_listesi : Fragment() {
    lateinit var viewmodel:besin_listesi_viewmodel
    var recycler_Adapter=recycler_adapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_besin_listesi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel=ViewModelProviders.of(this).get(besin_listesi_viewmodel::class.java)

        viewmodel.refreshdata()
        println("5")
        recyclerView.layoutManager=LinearLayoutManager(context)
        println("6")
        recyclerView.adapter=recycler_Adapter
        println("7")
        swipe_refresh_layout.setOnRefreshListener {
            swipe_refresh_layout.isRefreshing=false
            progress_bar.visibility=View.VISIBLE
            viewmodel.refreshdata()

        }
        ObserveLiveData()
    }
fun ObserveLiveData(){
    viewmodel.besinler.observe(viewLifecycleOwner, Observer {
        it?.let {
            println("9")
            println(viewmodel.besinler.value)
            recyclerView.visibility=View.VISIBLE
            println("9")
            recycler_Adapter.besin_listesi_guncelle(it)
            println("10")
        }
    })
   viewmodel.hata_mesaji.observe(viewLifecycleOwner, Observer {
        if (!it)
            hata_mesajı.visibility=View.GONE
        else {
            hata_mesajı.visibility = View.VISIBLE
        }
    })
    viewmodel.progress_bar.observe(viewLifecycleOwner, Observer {
        if (it) {
            progress_bar.visibility = View.VISIBLE
            hata_mesajı.visibility = View.GONE
            recyclerView.visibility = View.GONE
        }
        else{
            progress_bar.visibility = View.GONE

    }
    })
}

}