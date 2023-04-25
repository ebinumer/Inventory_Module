package com.ebinumer.inventorymodule.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query

interface GrnDao {

    @Insert
    suspend fun insert(addGrn: GrnEntity)

    @Query("SELECT * FROM grn_data ORDER BY id DESC")
    fun getAllGrnData(): LiveData<List<GrnEntity>>
}