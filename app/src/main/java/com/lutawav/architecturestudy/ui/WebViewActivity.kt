package com.lutawav.architecturestudy.ui

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.lutawav.architecturestudy.R
import kotlinx.android.synthetic.main.activity_webview.*

class WebViewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val url = intent.getStringExtra(EXTRA_URL)
            ?: run {
                // 전달된 값이 없으면 activity 종료
                finish()
                return
            }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val webView = web_view.apply {
            webViewClient = WebViewClient()
            settings.run {
                displayZoomControls = true
                useWideViewPort = true
                javaScriptEnabled = true
                domStorageEnabled = true
            }
        }
        webView.loadUrl(url)

    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_URL = "url"
    }
}