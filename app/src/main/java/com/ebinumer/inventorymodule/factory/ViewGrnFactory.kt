package com.ebinumer.inventorymodule.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ebinumer.inventorymodule.data.dataBase.GrnRepository
import com.ebinumer.inventorymodule.viewModel.ViewGrnViewModel


class ViewGrnFactory (
    private  val repository: GrnRepository,
    private val application: Application
    ): ViewModelProvider.Factory{
        @Suppress("Unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(ViewGrnViewModel::class.java)) {
                return ViewGrnViewModel(repository, application) as T
            }
            throw IllegalArgumentException("Unknown View Model Class")
        }
    }