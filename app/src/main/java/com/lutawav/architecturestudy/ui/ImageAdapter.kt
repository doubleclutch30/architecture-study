package com.lutawav.architecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lutawav.architecturestudy.data.Blog
import com.lutawav.architecturestudy.data.Image
import com.lutawav.architecturestudy.databinding.ItemBlogBinding
import com.lutawav.architecturestudy.databinding.ItemImageBinding

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {

    private var data = arrayListOf<Image>()

    inner class ViewHolder(private val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(image: Image) {
                binding.imageTitle.text = image.title

                Glide.with(binding.imageThumbnail.context)
                    .load(image.thumbnail)
                    .into(binding.imageThumbnail)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.ViewHolder {
        return ViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setItems(items: List<Image>) {
        data.addAll(items)
        notifyDataSetChanged()
    }
}