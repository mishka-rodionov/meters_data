package com.rodionov.meter_creator.presentation.start_creator.adapters

import androidx.recyclerview.widget.DiffUtil
import com.rodionov.domain.models.Flat

class StartCreatorDiffUtil(
    private val oldList: List<Flat>,
    private val newList: List<Flat>
): DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

}