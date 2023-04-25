package com.ebinumer.inventorymodule.viewModel

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ebinumer.inventorymodule.data.dataBase.GrnItemsEntity
import com.ebinumer.inventorymodule.data.dataBase.GrnRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ViewGrnViewModel(private val repository: GrnRepository, application: Application) :
    AndroidViewModel(application), Observable {

    val allGrn = repository.allGrn

    @Bindable
    val inputGrnNumber = MutableLiveData<String?>()

    @Bindable
    val inputGrnDate = MutableLiveData<String?>()

    val inputGrnItems = MutableLiveData<List<GrnItemsEntity>>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val _errorMsg = MutableLiveData<String>()
    val errorString: LiveData<String>
        get() = _errorMsg

    val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _navigateTo = MutableLiveData<Boolean>()

    val navigateTo: LiveData<Boolean>
        get() = _navigateTo

    private val _errorToastInvalidPassword = MutableLiveData<Boolean>()

    val errorToastInvalidPassword: LiveData<Boolean>
        get() = _errorToastInvalidPassword



    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
    }


}