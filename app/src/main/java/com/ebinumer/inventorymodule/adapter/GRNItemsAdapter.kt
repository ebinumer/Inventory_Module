package com.ebinumer.inventorymodule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ebinumer.inventorymodule.R
import com.ebinumer.inventorymodule.data.dataBase.GrnItemsEntity
import com.ebinumer.inventorymodule.databinding.GrnItemBinding

class GRNItemsAdapter(private  var grnData:List<GrnItemsEntity>): RecyclerView.Adapter<GRNItemsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.grn_item,parent,false))

    }

    override fun getItemCount(): Int = grnData.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        grnData[position].let {
            holder.bind(it)
        }

    }

    class MyViewHolder(private val binding: GrnItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: GrnItemsEntity) {
            binding.apply {
//            data = user
                binding.apply {
                    user.apply {
                        edt.text = Name
                        emailTextView.text = "Email : $email"
                        userTextField.text = "Mob : $mobile"
                    }

                }
            }
        }
    }
}