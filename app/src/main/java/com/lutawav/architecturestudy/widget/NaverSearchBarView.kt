package com.lutawav.architecturestudy.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.lutawav.architecturestudy.R
import com.lutawav.architecturestudy.databinding.ViewSearchBinding
import com.lutawav.architecturestudy.util.hideKeyboard

class NaverSearchBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val binding: ViewSearchBinding = DataBindingUtil.inflate<ViewSearchBinding>(
        LayoutInflater.from(context),
        R.layout.view_search,
        this,
        true
    )

    private val debounceTime: Long = 600L

    private var lastClickTime: Long = 0

    var onClickAction: ((String) -> Unit)? = null

    var keyword: String? = null
        set(value) {
            if (field != value) {
                field = value
                binding.searchEditor.setText(value)
            }
        }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_search, this, true)

        binding.searchBtn.setOnClickListener {
            if (System.currentTimeMillis() - lastClickTime < debounceTime) {
                return@setOnClickListener
            }
            val keyword = binding.searchEditor.text.toString()

            if (keyword.isBlank()) {
                Toast.makeText(
                    context,
                    context.getString(R.string.warn_input_keyword),
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                hideKeyboard()
                onClickAction?.invoke(keyword)
            }

        }
    }
}