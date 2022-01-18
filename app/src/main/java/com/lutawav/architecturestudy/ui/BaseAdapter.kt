package com.lutawav.architecturestudy.ui

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, H: BaseViewHolder<T>>
    : RecyclerView.Adapter<H>() {
    private val items: MutableList<T> = mutableListOf()


    override fun onBindViewHolder(holder: H, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    open fun setData(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }
}

abstract class BaseViewHolder<T>(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    abstract fun bind(item: T)
}