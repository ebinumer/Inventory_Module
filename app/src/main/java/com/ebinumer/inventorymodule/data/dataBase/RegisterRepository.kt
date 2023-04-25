package com.ebinumer.inventorymodule.data.dataBase


class RegisterRepository(private val dao: RegisterDatabaseDao) {

    val users = dao.getAllUsers()

    //add user
    suspend fun insert(user: RegisterEntity) {
        return dao.insert(user)
    }

    //get all users
    suspend fun getUserName(userName: String): RegisterEntity?{
        return dao.getUsername(userName)
    }

    //delete all users
    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}