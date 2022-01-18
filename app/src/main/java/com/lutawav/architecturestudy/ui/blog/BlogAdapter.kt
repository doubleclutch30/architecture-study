package com.lutawav.architecturestudy.ui.blog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.data.Blog
import com.lutawav.architecturestudy.databinding.ItemBlogBinding
import com.lutawav.architecturestudy.ui.BaseAdapter
import com.lutawav.architecturestudy.ui.BaseViewHolder

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

internal class BlogViewHolder(private val binding: ItemBlogBinding) :
    BaseViewHolder<Blog>(binding) {

    override fun bind(blog: Blog) {
        binding.blogTitle.text =
            HtmlCompat.fromHtml(blog.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding.blogDescription.text =
            HtmlCompat.fromHtml(blog.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding.blogOwner.text = blog.bloggerName
        binding.blogPostdate.text = blog.postdate
    }
}