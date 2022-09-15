package com.rodionov.meter_data_input.presentation.meters.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rodionov.meter_data_input.data.dto.MeterItem
import com.rodionov.ui.databinding.ItemMeterListBinding

class MeterListAdapter : RecyclerView.Adapter<MeterListAdapter.MeterListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeterListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MeterListViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class MeterListViewHolder(private val binding: ItemMeterListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MeterItem) {

        }

    }

}