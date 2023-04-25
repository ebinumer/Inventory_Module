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
import androidx.recyclerview.widget.LinearLayoutManager
import com.ebinumer.inventorymodule.R
import com.ebinumer.inventorymodule.data.dataBase.InventoryDatabase
import com.ebinumer.inventorymodule.data.dataBase.RegisterRepository
import com.ebinumer.inventorymodule.databinding.UserDetailsFragmentBinding
import com.ebinumer.inventorymodule.factory.UserDetalisFactory
import com.ebinumer.inventorymodule.viewModel.UserDetailsViewModel


class UserDetailsFragment : Fragment() {

    private lateinit var userDetailsViewModel: UserDetailsViewModel
    private lateinit var binding: UserDetailsFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.user_details_fragment, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val dao = InventoryDatabase.getInstance(application).registerDatabaseDao
        val repository = RegisterRepository(dao)
        val factory = UserDetalisFactory(repository, application)

        userDetailsViewModel =
            ViewModelProvider(this, factory)[UserDetailsViewModel::class.java]

//        binding.apply {
//            userDelailsLayout = userDetailsViewModel
//            lifecycleOwner = this@UserDetailsFragment
//        }


        userDetailsViewModel.navigateto.observe(viewLifecycleOwner) { hasFinished ->
//            if (hasFinished) {
//                NavHostFragment.findNavController(this)
//                    .navigate(UserDetailsFragmentDirections.actionUserDetailsFragmentToLoginFragment())
//                userDetailsViewModel.doneNavigating()
//            }
        }
        initUi()
        initRecyclerView()
    }

    private fun initUi() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finishAffinity()
                }

            })
    }


    private fun initRecyclerView() {
//        binding.usersRecyclerView.layoutManager = LinearLayoutManager(this.context)
//        displayUsersList()
    }


    private fun displayUsersList() {
        Log.i("MYTAG", "Inside ...UserDetails..Fragment")
        userDetailsViewModel.users.observe(viewLifecycleOwner) {
//            binding.usersRecyclerView.adapter = MyRecycleViewAdapter(it)
        }

    }

}