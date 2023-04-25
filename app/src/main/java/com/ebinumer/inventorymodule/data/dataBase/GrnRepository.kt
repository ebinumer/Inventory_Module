package com.ebinumer.inventorymodule.data.dataBase

class GrnRepository(private val dao:GrnDao) {

    //add grn data
    suspend fun insert(grnData: GrnEntity) {
        return dao.insert(grnData)
    }

    //get all grn
    val allGrn = dao.getAllGrnData()


}