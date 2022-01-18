package com.lutawav.architecturestudy.ui.image

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.lutawav.architecturestudy.data.model.Image
import com.lutawav.architecturestudy.databinding.ItemImageBinding
import com.lutawav.architecturestudy.ui.BaseAdapter
import com.lutawav.architecturestudy.ui.BaseViewHolder
import com.lutawav.architecturestudy.util.startWebView

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

        binding.root.setOnClickListener { view ->
            view.startWebView(image.link)
        }
    }

}