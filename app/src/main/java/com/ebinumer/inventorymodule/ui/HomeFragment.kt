package com.ebinumer.inventorymodule.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.ebinumer.inventorymodule.R
import com.ebinumer.inventorymodule.data.dataBase.InventoryDatabase
import com.ebinumer.inventorymodule.data.dataBase.RegisterRepository
import com.ebinumer.inventorymodule.databinding.HomeFragmentBinding
import com.ebinumer.inventorymodule.factory.HomeFactory
import com.ebinumer.inventorymodule.viewModel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var userDetailsViewModel: HomeViewModel
    private lateinit var binding: HomeFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.home_fragment, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dao = InventoryDatabase.getInstance(application).registerDatabaseDao
        val repository = RegisterRepository(dao)
        val factory = HomeFactory(repository, application)

        userDetailsViewModel =
            ViewModelProvider(this, factory)[HomeViewModel::class.java]

        binding.apply {
           addGrn.setOnClickListener {
               NavHostFragment.findNavController(this@HomeFragment)
                   .navigate(HomeFragmentDirections.actionHomeFragmentToRegisterGoodsFragment())
           }
            layoutViewGrn.setOnClickListener {
                NavHostFragment.findNavController(this@HomeFragment)
                    .navigate(HomeFragmentDirections.actionHomeFragmentToViewGoodsFragment())
            }

        }

        initUi()
    }

    private fun initUi() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finishAffinity()
                }

            })
    }

}