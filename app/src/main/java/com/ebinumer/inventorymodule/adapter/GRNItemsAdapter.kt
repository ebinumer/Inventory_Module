package com.ebinumer.inventorymodule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ebinumer.inventorymodule.R
import com.ebinumer.inventorymodule.data.dataBase.GrnItemsEntity
import com.ebinumer.inventorymodule.databinding.GrnItemBinding

class GRNItemsAdapter(private var grnData:List<GrnItemsEntity>,
                      var onItemDltClicked: (GrnItemsEntity, Int) -> Unit
):
    RecyclerView.Adapter<GRNItemsAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.grn_item,parent,false))

    }

    override fun getItemCount(): Int = grnData.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        grnData[position].let {
            holder.bind(it,position,onItemDltClicked)
        }

    }

    class MyViewHolder(private val binding: GrnItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            data: GrnItemsEntity,
            position: Int,
            onItemDltClicked: (data: GrnItemsEntity, position: Int) -> Unit
        ) {
            binding.apply {
                itemData = data
                    closeBtn.setOnClickListener {
                        onItemDltClicked(data,position)
                    }

            }
        }

    }
}