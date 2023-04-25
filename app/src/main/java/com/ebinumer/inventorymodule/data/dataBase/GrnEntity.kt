package com.ebinumer.inventorymodule.data.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "grn_data")
data class GrnEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "grn_number")
    var GrnNumber: String,

    @ColumnInfo(name = "grn_date")
    var GrnDate: String,

    @ColumnInfo(name = "grn_items")
    var GrnItems: List<GrnItemsEntity>
)

data class GrnItemsEntity (

    @ColumnInfo(name = "grn_item_code")
    var GrnItemCode: String,

    @ColumnInfo(name = "grn_item_name")
    var GrnItemName: String,

    @ColumnInfo(name = "grn_items_qty")
    var GrnItemsQty: String
)
