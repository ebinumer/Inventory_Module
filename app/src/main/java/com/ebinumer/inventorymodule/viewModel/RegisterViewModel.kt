package com.ebinumer.inventorymodule.viewModel

import android.app.Application
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.ebinumer.inventorymodule.data.dataBase.RegisterEntity
import com.ebinumer.inventorymodule.data.dataBase.RegisterRepository
import kotlinx.coroutines.*

class RegisterViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

    init {
        Log.i("RegisterViewModel", "init")
    }

    private var userdata: String? = null

    var userDetailsLiveData = MutableLiveData<Array<RegisterEntity>>()

    @Bindable
    val inputName = MutableLiveData<String?>()

    @Bindable
    val inputUsername = MutableLiveData<String?>()

    @Bindable
    val inputPassword = MutableLiveData<String?>()

    @Bindable
    val inputCnfPassword = MutableLiveData<String?>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _navigateTo = MutableLiveData<Boolean>()

    val navigateTo: LiveData<Boolean>
        get() = _navigateTo

    private val _errorToast = MutableLiveData<Boolean>()

    val errorToast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errorToastUsername: LiveData<Boolean>
        get() = _errorToastUsername

    private val _errorMsg = MutableLiveData<String>()
    val errorString: LiveData<String>
        get() = _errorMsg

    fun submitButton() {
        Log.i("RegisterViewModel", "Inside SUBMIT BUTTON")
        if (inputName.value == null) {
            _errorMsg.value="Name"
            _errorToast.value = true
        }  else if (inputUsername.value == null) {
            _errorMsg.value="User Name"
            _errorToast.value = true
        } else if (inputPassword.value == null ) {
            _errorMsg.value="Password"
            _errorToast.value = true
        }else if (inputCnfPassword.value == null ) {
            _errorMsg.value="Conform Password"
            _errorToast.value = true
        }else if (inputPassword.value != inputCnfPassword.value ) {
            _errorMsg.value="Password Mismatch"
            _errorToast.value = true
        }
        else {
            uiScope.launch {
//            withContext(Dispatchers.IO) {
                val usersNames = repository.getUserName(inputUsername.value!!)
                if (usersNames != null) {
                    _errorToastUsername.value = true
                } else {

                    insert(RegisterEntity(0, inputName.value!!, inputUsername.value!!, inputPassword.value!!))
                    inputName.value = null
                    inputUsername.value = null
                    inputPassword.value = null
                    inputCnfPassword.value = null
                    _navigateTo.value = true
                }
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun doneNavigating() {
        _navigateTo.value = false
        Log.i("RegisterViewModel", "Done navigating ")
    }

    fun donetoast() {
        _errorToast.value = false
        _errorMsg.value = ""
    }

    fun donetoastUserName() {
        _errorToast.value = false

    }

    private fun insert(user: RegisterEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

//    fun clearALl():Job = viewModelScope.launch {
//        repository.deleteAll()
//    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}



