package com.msk.besinleruygulamas.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.msk.besinleruygulamas.R
import com.msk.besinleruygulamas.model.besin
import kotlinx.android.synthetic.main.fragment_besin_detayi.view.*
import kotlinx.android.synthetic.main.recycler_row.view.*
import java.util.zip.Inflater

class recycler_adapter(var besin_listesi:ArrayList<besin>): RecyclerView.Adapter<recycler_adapter.Besin_Viewholder>() {
    class Besin_Viewholder(var view:View):RecyclerView.ViewHolder(view) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Besin_Viewholder {

        val  inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_row,parent,false)
        return Besin_Viewholder(view)
    }

    override fun onBindViewHolder(holder: Besin_Viewholder, position: Int) {
        holder.view.besin_ismi.text=besin_listesi.get(position).besin_isim
        holder.view.besin_kalorisi.text=besin_listesi.get(position).besin_isim

    }

    override fun getItemCount(): Int {
        return besin_listesi.size
    }

    fun besin_listesi_guncelle(liste:List<besin>){
        besin_listesi.clear()
        besin_listesi.addAll(liste)
        notifyDataSetChanged()


    }
}