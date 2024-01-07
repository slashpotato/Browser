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
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        val buttonGo: Button = findViewById(id.buttonGo)
        val buttonNew: ImageButton = findViewById(id.imageButtonNew)
        val editText: EditText = findViewById(id.editTextText)
        val webView: WebView = findViewById(id.webView)

        val homeUrl: String = "https://www.google.com/"

        fun goHome() {
            webView.loadUrl(homeUrl)
        }
        fun alog(content: Any) {
            Log.i("App", content.toString())
        }

        webView.settings.userAgentString = ""

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
                // do your handling codes here, which url is the requested url
                // probably you need to open that url rather than redirect:
                if (url == "") {
                    goHome()
                }
                view.loadUrl(url)
                alog("redirected to $url")
                editText.setText(webView.url)
                return false // then it is not handled by default action
            }
        }

        goHome()
    }
}