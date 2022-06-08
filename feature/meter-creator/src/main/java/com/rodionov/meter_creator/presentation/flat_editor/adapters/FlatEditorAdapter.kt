package com.rodionov.meter_creator.presentation.flat_editor.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rodionov.domain.models.Meter
import com.rodionov.domain.models.MeterType
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.databinding.ItemMeterEditorBinding

class FlatEditorAdapter(private val onClickListener: (Meter) -> Unit) :
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
        holder.bind(getItem(position))
    }

    inner class MeterEditorViewHolder(val binding: ItemMeterEditorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meter: Meter) {
            binding.root.setOnClickListener { onClickListener.invoke(meter) }
            binding.tvMeterEditorItem.text = meter.name
            when (meter.type) {
                MeterType.GAS -> {
                    binding.tvMeterEditorItem.setCompoundDrawablesWithIntrinsicBounds(
                        com.rodionov.ui.R.drawable.ic_local_fire,
                        0,
                        0,
                        0
                    )
                    binding.tvMeterEditorItem.setCompoundDrawableTintList(
                        ColorStateList.valueOf(
                            binding.root.context.resources.getColor(com.rodionov.ui.R.color.colorGas)
                        )
                    )
                }
                MeterType.ELECTRIC -> {
                    binding.tvMeterEditorItem.setCompoundDrawablesWithIntrinsicBounds(
                        com.rodionov.ui.R.drawable.ic_lightbulb,
                        0,
                        0,
                        0
                    )
                    binding.tvMeterEditorItem.setCompoundDrawableTintList(
                        ColorStateList.valueOf(
                            binding.root.context.resources.getColor(com.rodionov.ui.R.color.colorLight)
                        )
                    )
                }
                MeterType.COLD_WATER -> {
                    binding.tvMeterEditorItem.setCompoundDrawablesWithIntrinsicBounds(
                        com.rodionov.ui.R.drawable.ic_water_drop,
                        0,
                        0,
                        0
                    )
                    binding.tvMeterEditorItem.setCompoundDrawableTintList(
                        ColorStateList.valueOf(
                            binding.root.context.resources.getColor(com.rodionov.ui.R.color.colorColdWater)
                        )
                    )
                }
                MeterType.HOT_WATER -> {
                    binding.tvMeterEditorItem.setCompoundDrawablesWithIntrinsicBounds(
                        com.rodionov.ui.R.drawable.ic_water_drop,
                        0,
                        0,
                        0
                    )
                    binding.tvMeterEditorItem.setCompoundDrawableTintList(
                        ColorStateList.valueOf(
                            binding.root.context.resources.getColor(com.rodionov.ui.R.color.colorHotWater)
                        )
                    )
                }
            }
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