package com.ebinumer.inventorymodule.ui

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ebinumer.inventorymodule.R
import com.ebinumer.inventorymodule.adapter.GrnItemViewAdapter
import com.ebinumer.inventorymodule.data.dataBase.GrnEntity
import com.ebinumer.inventorymodule.data.dataBase.GrnItemsEntity
import com.ebinumer.inventorymodule.data.dataBase.GrnRepository
import com.ebinumer.inventorymodule.data.dataBase.InventoryDatabase
import com.ebinumer.inventorymodule.databinding.ViewGrnFragmentBinding
import com.ebinumer.inventorymodule.factory.ViewGrnFactory
import com.ebinumer.inventorymodule.viewModel.ViewGrnViewModel


class ViewGrnFragment : Fragment() {
    private lateinit var viewViewModel: ViewGrnViewModel
    lateinit var mBinding: ViewGrnFragmentBinding
    var grnData: MutableList<GrnEntity> = mutableListOf<GrnEntity>()
    var spinnerId: Array<String> = emptyArray()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.view_grn_fragment, container, false
        )

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dao = InventoryDatabase.getInstance(application).grnDao
        val repository = GrnRepository(dao)
        val factory = ViewGrnFactory(repository, application)


        viewViewModel = ViewModelProvider(this, factory)[ViewGrnViewModel::class.java]
        mBinding.apply {
            viewmodel = viewViewModel
            lifecycleOwner = this@ViewGrnFragment
            Log.e("dattaaa", "${viewViewModel.allGrn.value}")
            edtGrnNumber.apply {
                isFocusableInTouchMode = false
                isClickable = false
                isLongClickable = false
                isEnabled = false
            }
            edtGrnDate.apply {
                isFocusableInTouchMode = false
                isClickable = false
                isLongClickable = false
                isEnabled = false
            }
                edtLayoutGrnNumber.setOnClickListener {
                initSpinner()
            }
        }

        observers()

    }

    private fun initSpinner() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Select an option")
        builder.setItems(spinnerId) { dialog, which ->
            mBinding.apply {
                edtGrnNumber.setText(spinnerId[which])
                viewItems.visibility = View.VISIBLE
            }

            setSelected(which)

        }
        builder.show()

    }

    private fun setSelected(which: Int) {
       val  selectedData = grnData[which]

        val grnItemsAdapter = GrnItemViewAdapter(selectedData.GrnItems) { data: GrnItemsEntity, position :Int ->

        }
        mBinding.apply {
            edtGrnDate.setText(selectedData.GrnDate)
            viewRecycler.adapter = grnItemsAdapter
        }



    }


    private fun observers() {
        viewViewModel.allGrn.observe(viewLifecycleOwner) {
            Log.e("items", "${it}")
            it.forEachIndexed { index, grnEntity ->
                spinnerId += grnEntity.GrnNumber
                grnData.add(grnEntity)
            }

        }

    }
}