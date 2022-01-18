package com.lutawav.architecturestudy.ui.image

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lutawav.architecturestudy.data.Image
import com.lutawav.architecturestudy.databinding.ItemImageBinding
import com.lutawav.architecturestudy.ui.BaseAdapter
import com.lutawav.architecturestudy.ui.BaseViewHolder

internal class ImageAdapter : BaseAdapter<Image, ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

internal class ImageViewHolder(private val binding: ItemImageBinding) :
    BaseViewHolder<Image>(binding) {

    override fun bind(image: Image) {
        binding.imageTitle.text = image.title

        Glide.with(binding.imageThumbnail.context)
            .load(image.thumbnail)
            .into(binding.imageThumbnail)
    }

}