package com.lutawav.architecturestudy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.lutawav.architecturestudy.data.Blog
import com.lutawav.architecturestudy.databinding.ItemBlogBinding

class BlogAdapter : RecyclerView.Adapter<BlogAdapter.ViewHolder>() {

    private var data = arrayListOf<Blog>()

    inner class ViewHolder(private val binding: ItemBlogBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(blog: Blog) {
            binding.blogTitle.text =
                HtmlCompat.fromHtml(blog.title, HtmlCompat.FROM_HTML_MODE_COMPACT)
            binding.blogDescription.text =
                HtmlCompat.fromHtml(blog.description, HtmlCompat.FROM_HTML_MODE_COMPACT)
            binding.blogOwner.text = blog.bloggerName
            binding.blogPostdate.text = blog.postdate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogAdapter.ViewHolder {
        return ViewHolder(
            ItemBlogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    fun setItems(items: List<Blog>) {
        data.addAll(items)
        notifyDataSetChanged()
    }
}