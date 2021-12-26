package com.msk.besinleruygulamas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class besin(
    @ColumnInfo(name = "isim")
    @SerializedName("isim")
    var besin_isim:String?,
    @ColumnInfo(name = "kalori")
    @SerializedName("kalori")
    var besin_kalori:String?,
    @ColumnInfo(name = "karbonhidrat")
    @SerializedName("karbonhidrat")
    var besin_karbonhidrat:String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    var besin_protein:String?,
    @ColumnInfo(name = "yag")
    @SerializedName("yag")
    var besin_yagi:String,
    @ColumnInfo(name = "gorsel")
    @SerializedName("gorsel")
    var besin_gorseli:String)
{

    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}