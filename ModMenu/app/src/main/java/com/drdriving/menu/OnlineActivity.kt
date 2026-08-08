package com.drdriving.menu

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("SetJavaScriptEnabled")
class OnlineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val web = WebView(this)
        web.settings.javaScriptEnabled = true      // ضروري للخريطة
        web.settings.domStorageEnabled = true
        web.settings.mediaPlaybackRequiresUserGesture = false
        web.webViewClient = WebViewClient()

        // ⬇️⬇️ ضع رابط سيرفر Render الخاص بك هنا ⬇️⬇️
        web.loadUrl("https://YOUR-SERVER.onrender.com")

        setContentView(web)
    }
}
