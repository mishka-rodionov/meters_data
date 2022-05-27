package com.rodionov.meter_creator.presentation.start_creator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rodionov.domain.models.Flat
import com.rodionov.domain.models.MeterType
import com.rodionov.meter_creator.R
import com.rodionov.meter_creator.databinding.ItemFlatBinding

class StartCreatorAdapter(
    private val clickListener: (Flat) -> Unit,
    private val removeListener: (String) -> Unit,
    private val editListener: (String) -> Unit
) :
    RecyclerView.Adapter<StartCreatorAdapter.StartCreatorViewHolder>() {

    var flats: List<Flat> = emptyList()
        set(newValue) {
            val diffCallback = StartCreatorDiffUtil(field, newValue)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            field = newValue
            diffResult.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StartCreatorViewHolder {
        return StartCreatorViewHolder(
            ItemFlatBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: StartCreatorViewHolder, position: Int) {
        holder.bind(flats[position])
    }

    override fun getItemCount() = flats.size

    inner class StartCreatorViewHolder(val binding: ItemFlatBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(flat: Flat) {
            binding.tvFlatName.text = flat.name
            binding.tvFlatAddress.text = flat.address
            binding.root.setOnClickListener { clickListener.invoke(flat) }
            binding.ivFlatRemove.setOnClickListener { removeListener.invoke(flat.id) }
            binding.ivFlatEdit.setOnClickListener { editListener.invoke(flat.id) }
            flat.meters?.let { meters ->
                MeterType.values().forEach { meterType ->
                    meters.count { it.type == meterType }.run {
                        when(meterType) {
                            MeterType.COLD_WATER -> setMeterIndicator(this, binding.tvColdWaterIndicator)
                            MeterType.HOT_WATER -> setMeterIndicator(this, binding.tvHotWaterIndicator)
                            MeterType.GAS -> setMeterIndicator(this, binding.tvGasIndicator)
                            MeterType.ELECTRIC -> setMeterIndicator(this, binding.tvLightIndicator)
                        }
                    }
                }
            }
        }

        private fun setMeterIndicator(number: Int, textView: TextView) {
            if (number > 0) {
                textView.isVisible = true
                textView.text = binding.root.context.getString(
                    com.rodionov.ui.R.string.pattern_meter_indicator,
                    number.toString()
                )
            } else {
                textView.isVisible = false
            }
        }
    }

}