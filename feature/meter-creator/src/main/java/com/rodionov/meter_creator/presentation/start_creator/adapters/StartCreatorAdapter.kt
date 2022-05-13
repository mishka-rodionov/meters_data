package com.rodionov.meter_creator.presentation.start_creator.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.rodionov.domain.models.Flat
import com.rodionov.meter_creator.databinding.ItemFlatBinding

class StartCreatorAdapter(
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
                binding.ivFlatRemove.setOnClickListener { removeListener.invoke(flat.id) }
                binding.ivFlatEdit.setOnClickListener { editListener.invoke(flat.id) }
            }

    }

}