package com.gentalha.pocview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gentalha.pocview.LoginScript.scripts
import com.gentalha.pocview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val urlLoginPadrao = "192.168.15.1/padrao"
    private val urlLogin = "http://192.168.15.1/cgi-bin/login_advance.cgi"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.webView.apply {
            settings.javaScriptEnabled = true
            loadUrl(urlLogin)
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    println("THG_LOG current url-> $url")

                    if (url == urlLogin || url == urlLoginPadrao) {
                        scripts.forEach { script ->
                            println("THG_LOG --> $script")
                            evaluateJavascript(script) { result ->
                                println("THG_LOG --> $result")
                            }
                        }
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Etapa de LOGIN finalizada com SUCESSO!!",
                            Toast.LENGTH_LONG
                        ).show()
                        binding.progress.hide()
                        binding.textView.show()
                    }
                }

            }

        }
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}