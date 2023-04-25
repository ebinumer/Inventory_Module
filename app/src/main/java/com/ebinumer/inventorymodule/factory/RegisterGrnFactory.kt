package com.ebinumer.inventorymodule.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ebinumer.inventorymodule.data.dataBase.GrnRepository
import com.ebinumer.inventorymodule.viewModel.RegisterGrnViewModel


class RegisterGrnFactory (
    private  val repository: GrnRepository,
    private val application: Application
): ViewModelProvider.Factory{
    @Suppress("Unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(RegisterGrnViewModel::class.java)) {
            return RegisterGrnViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}