package com.rodionov.meter_data_input.presentation.meters.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rodionov.domain.models.MeterType
import com.rodionov.meter_data_input.R
import com.rodionov.meter_data_input.data.dto.MeterItem
import com.rodionov.ui.databinding.ItemMeterListBinding

class MeterListAdapter : RecyclerView.Adapter<MeterListAdapter.MeterListViewHolder>() {

    var itemList: List<MeterItem> = emptyList()
        set(value) {
            val diffUtilCallback = MeterListDiffUtilCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeterListViewHolder {
        return MeterListViewHolder(ItemMeterListBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MeterListViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    inner class MeterListViewHolder(private val binding: ItemMeterListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MeterItem) {
            binding.tvMeterName.text = item.name
            binding.tvFlatAddress.text = item.address
            binding.tvMeterLastData.text = item.lastData
            when (item.meterType) {
                MeterType.GAS -> {
                    binding.ivMeterType.apply {
                        setImageResource(com.rodionov.ui.R.drawable.ic_local_fire)
                        imageTintList = context.resources.getColorStateList(
                            com.rodionov.ui.R.color.colorGas,
                            context.theme
                        )
                    }
                }
                MeterType.ELECTRIC -> {
                    binding.ivMeterType.apply {
                        setImageResource(com.rodionov.ui.R.drawable.ic_lightbulb)
                        imageTintList = context.resources.getColorStateList(
                            com.rodionov.ui.R.color.colorLight,
                            context.theme
                        )
                    }
                }
                MeterType.COLD_WATER -> {
                    binding.ivMeterType.apply {
                        setImageResource(com.rodionov.ui.R.drawable.ic_water_drop)
                        imageTintList = context.resources.getColorStateList(
                            com.rodionov.ui.R.color.colorColdWater,
                            context.theme
                        )
                    }
                }
                MeterType.HOT_WATER -> {
                    binding.ivMeterType.apply {
                        setImageResource(com.rodionov.ui.R.drawable.ic_water_drop)
                        imageTintList = context.resources.getColorStateList(
                            com.rodionov.ui.R.color.colorHotWater,
                            context.theme
                        )
                    }
                }
            }
        }

    }

}