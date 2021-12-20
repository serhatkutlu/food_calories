package com.msk.besinleruygulamas.model

import com.google.gson.annotations.SerializedName

data class besin(
    @SerializedName("isim")
    var besin_isim:String?,
    @SerializedName("kalori")
    var besin_kalori:String?,
    @SerializedName("karbonhidrat")
    var besin_karbonhidrat:String?,
    @SerializedName("protein")
    var besin_protein:String?,
    @SerializedName("yag")
    var besin_yagi:String,
    @SerializedName("gorsel")
    var besin_gorseli:String) {
}