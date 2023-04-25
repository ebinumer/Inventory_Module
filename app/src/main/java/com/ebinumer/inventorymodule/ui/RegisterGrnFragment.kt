package com.ebinumer.inventorymodule.ui

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.icu.util.Calendar
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
import com.ebinumer.inventorymodule.adapter.GRNItemsAdapter
import com.ebinumer.inventorymodule.data.dataBase.GrnItemsEntity
import com.ebinumer.inventorymodule.data.dataBase.GrnRepository
import com.ebinumer.inventorymodule.data.dataBase.InventoryDatabase
import com.ebinumer.inventorymodule.databinding.RegisterGoodsFragmentBinding
import com.ebinumer.inventorymodule.factory.RegisterGrnFactory
import com.ebinumer.inventorymodule.utils.showToast
import com.ebinumer.inventorymodule.viewModel.RegisterGrnViewModel


class RegisterGrnFragment : Fragment() {
    private lateinit var grnRegisterViewModel: RegisterGrnViewModel
    lateinit var mBinding : RegisterGoodsFragmentBinding
    var grnData:MutableList<GrnItemsEntity>  = mutableListOf<GrnItemsEntity>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.register_goods_fragment, container, false
        )

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dao = InventoryDatabase.getInstance(application).grnDao
        val repository = GrnRepository(dao)
        val factory = RegisterGrnFactory(repository, application)
        grnData.add(
            GrnItemsEntity(GrnItemCode = "", GrnItemName = "", GrnItemsQty = ""),
        )

        grnRegisterViewModel = ViewModelProvider(this, factory)[RegisterGrnViewModel::class.java]
        mBinding.apply {
            viewmodel = grnRegisterViewModel
            lifecycleOwner = this@RegisterGrnFragment
            recycleGrnItems.apply {
                adapter = grnItemsAdapter
            }
            btnAdd.setOnClickListener {
                grnData.add(
                    GrnItemsEntity(GrnItemCode = "", GrnItemName = "", GrnItemsQty =""),
                )
                grnItemsAdapter.notifyItemInserted(grnData.size-1)
                Log.e("Tag","btn clicked$grnData")
            }
            btnSave.setOnClickListener {
                grnRegisterViewModel.inputGrnItems.value = grnData
//                grnRegisterViewModel.inputGrnNumber.value = edtGrnNumber.text.toString()
//                grnRegisterViewModel.inputGrnDate.value = edtGrnDate.text.toString()
                grnRegisterViewModel.addButtonClick()
            }
            edtGrnDate.apply {
                isFocusableInTouchMode = false
                isClickable = false
                isLongClickable = false
                isEnabled = false
            }
            edtLayoutGrnDate.setOnClickListener {
                setDateTimeField()
            }

        }

        observers()

    }


    private var grnItemsAdapter = GRNItemsAdapter(grnData) { data: GrnItemsEntity,position :Int ->
        if(grnData.size > 1) {
            grnData.remove(data)
            test(position)
        }

    }


    private fun observers(){
        grnRegisterViewModel.navigateTo.observe(viewLifecycleOwner) { hasFinished ->
            if (hasFinished == true) {
                Log.i("add fragment", "insidi observe")
                NavHostFragment.findNavController(this@RegisterGrnFragment)
                    .navigate(RegisterGrnFragmentDirections.actionRegisterGoodsFragmentToHomeFragment())
            }
        }
        grnRegisterViewModel.errorToast.observe(viewLifecycleOwner) { hasError->
            if(hasError==true){

                    "Please fill ${grnRegisterViewModel.errorString.value} fields".showToast(requireContext())
                grnRegisterViewModel.donetoast()

            }
        }

    }
    fun test(pos:Int){
        grnItemsAdapter.notifyItemRemoved(pos)
    }
    private fun setDateTimeField() {
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDay = c[Calendar.DAY_OF_MONTH]


        val datePickerDialog = DatePickerDialog(requireContext(),
            OnDateSetListener { view, year, monthOfYear, dayOfMonth -> mBinding.edtGrnDate.setText("$dayOfMonth-${monthOfYear + 1}-$year") },
            mYear,
            mMonth,
            mDay
        )
        datePickerDialog.show()  }
    }
