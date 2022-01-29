package com.lutawav.architecturestudy.ui

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.lutawav.architecturestudy.R
import com.lutawav.architecturestudy.databinding.ActivityWebviewBinding

class WebViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.getStringExtra(EXTRA_URL)
            ?: run {
                // 전달된 값이 없으면 activity 종료
                finish()
                return
            }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_webview)
        binding.webView.apply {
            webViewClient = WebViewClient()
            settings.run {
                displayZoomControls = true
                useWideViewPort = true
                javaScriptEnabled = true
                domStorageEnabled = true
            }
        }
        binding.webView.loadUrl(url)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_URL = "url"
    }
}