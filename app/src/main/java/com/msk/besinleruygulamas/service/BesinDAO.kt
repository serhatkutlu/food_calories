package com.msk.besinleruygulamas.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.msk.besinleruygulamas.model.besin
@Dao
interface BesinDAO {


    @Insert
    suspend fun insert_all(vararg besin:besin):List<Long>

    @Query("SELECT * FROM besin")
    suspend fun getAllBesin():List<besin>

    @Query("SELECT * FROM besin WHERE id=:besinID")
    suspend fun getbesin(besinID:Int):besin

    @Query("DELETE FROM  besin")
    suspend fun deleteAllBesin()
}