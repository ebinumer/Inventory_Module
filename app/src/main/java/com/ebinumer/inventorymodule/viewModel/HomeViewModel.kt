package com.ebinumer.inventorymodule.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ebinumer.inventorymodule.data.dataBase.RegisterRepository

class HomeViewModel (private val repository: RegisterRepository, application: Application):
    AndroidViewModel(application){

    val users = repository.users
    init {
        Log.i("UserDetailsViewModel","inside_users_Lisrt_init")
    }
    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    fun doneNavigating(){
        _navigateto.value=false
    }

    fun backButtonClicked(){
        _navigateto.value = true
    }

}