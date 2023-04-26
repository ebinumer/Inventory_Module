package com.ebinumer.inventorymodule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ebinumer.inventorymodule.R
import com.ebinumer.inventorymodule.data.dataBase.GrnItemsEntity
import com.ebinumer.inventorymodule.databinding.ViewItemLayoutBinding


class GrnItemViewAdapter(
    private var grnData: List<GrnItemsEntity>,
    var onItemClicked: (GrnItemsEntity, Int) -> Unit
) : RecyclerView.Adapter<GrnItemViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MyViewHolder(
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.view_item_layout,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = grnData.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        grnData[position].let {
            holder.bind(it, position, onItemClicked)
        }

    }

    class MyViewHolder(private val binding: ViewItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            mData: GrnItemsEntity,
            position: Int,
            onItemClicked: (data: GrnItemsEntity, position: Int) -> Unit
        ) {
            binding.apply {
                data = mData
//                closeBtn.setOnClickListener {
//                    onItemDltClicked(data,position)
//                }

            }
        }

    }
}