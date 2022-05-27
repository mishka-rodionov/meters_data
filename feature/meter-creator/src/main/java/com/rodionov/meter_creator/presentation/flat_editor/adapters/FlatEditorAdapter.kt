package com.rodionov.meter_creator.presentation.flat_editor.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.MeterType
import com.rodionov.meter_creator.databinding.ItemMeterEditorBinding

class FlatEditorAdapter :
    ListAdapter<Meter, FlatEditorAdapter.MeterEditorViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeterEditorViewHolder {
        return MeterEditorViewHolder(
            ItemMeterEditorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MeterEditorViewHolder, position: Int) {

    }

    inner class MeterEditorViewHolder(val binding: ItemMeterEditorBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(meter: Meter) {
                binding.tvMeterEditorItem.text = meter.name
                when(meter.type) {
                    MeterType.GAS -> {

                    }
                    MeterType.ELECTRIC -> {

                    }
                    MeterType.COLD_WATER -> {

                    }
                    MeterType.HOT_WATER -> {

                    }
                }
                binding.tvMeterEditorItem.drawable
            }

        }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Meter>() {
            override fun areItemsTheSame(oldItem: Meter, newItem: Meter): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Meter, newItem: Meter): Boolean {
                return oldItem == newItem
            }
        }
    }

}