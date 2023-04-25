package com.ebinumer.inventorymodule.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.ebinumer.inventorymodule.R
import com.ebinumer.inventorymodule.data.dataBase.InventoryDatabase
import com.ebinumer.inventorymodule.data.dataBase.RegisterRepository
import com.ebinumer.inventorymodule.databinding.RegisterHomeFragmentBinding
import com.ebinumer.inventorymodule.factory.RegisterViewModelFactory
import com.ebinumer.inventorymodule.utils.showSnackBar
import com.ebinumer.inventorymodule.utils.showToast
import com.ebinumer.inventorymodule.viewModel.RegisterViewModel


class SignupFragment:Fragment() {
 private lateinit var registerViewModel: RegisterViewModel
 lateinit var mBinding : RegisterHomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.register_home_fragment, container, false
        )

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dao = InventoryDatabase.getInstance(application).registerDatabaseDao
        val repository = RegisterRepository(dao)
        val factory = RegisterViewModelFactory(repository, application)

        registerViewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]
        mBinding.apply {
            myViewModel = registerViewModel
            lifecycleOwner = this@SignupFragment
        }

        observers()

    }

    private fun displayUsersList() {
        NavHostFragment.findNavController(this).navigate(SignupFragmentDirections.actionRegisterFragmentToLogin())

    }

    private fun observers(){
        registerViewModel.navigateTo.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                Log.i("SignupFragment", "insidi observe")
                displayUsersList()
                registerViewModel.doneNavigating()
            }
        }

        registerViewModel.userDetailsLiveData.observe(viewLifecycleOwner) {
            Log.i("SignupFragment",it.toString()+"000000000000000000000000")
        }


        registerViewModel.errorToast.observe(viewLifecycleOwner) { hasError->
            if(hasError==true){
                if(registerViewModel.errorString.value == "Password Mismatch"){

                    " ${registerViewModel.errorString.value}".showToast(requireContext())
                }
                else {
                    "Please fill ${registerViewModel.errorString.value} fields".showToast(requireContext())
                }
                    registerViewModel.donetoast()

            }
        }

        registerViewModel.errorToastUsername.observe(viewLifecycleOwner) { hasError->
            if(hasError==true){
                mBinding.userNameTextField.error ="UserName Already taken"
//                "UserName Already taken".showToast(requireContext())
                registerViewModel.donetoastUserName()
            }
        }
    }
}