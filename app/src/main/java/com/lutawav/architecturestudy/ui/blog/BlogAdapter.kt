package com.lutawav.architecturestudy.ui.blog

import android.view.LayoutInflater
import android.view.ViewGroup
import com.lutawav.architecturestudy.data.model.Blog
import com.lutawav.architecturestudy.databinding.ItemBlogBinding
import com.lutawav.architecturestudy.ui.BaseAdapter
import com.lutawav.architecturestudy.ui.BaseViewHolder
import com.lutawav.architecturestudy.util.startWebView

internal class BlogAdapter : BaseAdapter<Blog, BlogViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        return BlogViewHolder(
            ItemBlogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}

internal class BlogViewHolder(
    private val binding: ItemBlogBinding
) : BaseViewHolder<Blog>(binding) {

    override fun bind(item: Blog) {
        binding.blog = item
        binding.executePendingBindings()

        binding.root.setOnClickListener { view ->
            view.startWebView(item.link)
        }
    }
}