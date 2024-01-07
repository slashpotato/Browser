package dev.slashpotato.browser

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import dev.slashpotato.browser.R.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val buttonGo: Button = findViewById(id.buttonGo)
        val buttonNew: ImageButton = findViewById(id.imageButtonNew)
        val editText: EditText = findViewById(id.editTextText)
        val webView: WebView = findViewById(id.webView)

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true

        val homeUrl: String = "https://www.google.com/"

        fun goHome() {
            webView.loadUrl(homeUrl)
        }
        fun alog(content: Any) {
            Log.i("App Log", content.toString())
        }

        buttonGo.setOnClickListener {
            var url = editText.text.toString()
            if (url == "") {
                url = homeUrl
            } else if (!url.contains("https://") && !url.contains("http://")) {
                url = "$homeUrl/search?q=$url"
            }
            webView.loadUrl(url)
        }

        buttonNew.setOnClickListener {
            goHome()
        }

        webView.webViewClient = object : WebViewClient() {
            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url == "") {
                    goHome()
                }
                view.loadUrl(url)
                editText.setText(webView.url)
                return false
            }
        }

        goHome()
    }
}