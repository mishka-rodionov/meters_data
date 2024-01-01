package com.rodionov.meter_data_input.presentation.meters.adapters

import androidx.recyclerview.widget.DiffUtil
import com.rodionov.meter_data_input.data.dto.MeterItem

class MeterListDiffUtilCallback(
    private val oldList: List<MeterItem>,
    private val newList: List<MeterItem>
): DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].lastData == newList[newItemPosition].lastData
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}