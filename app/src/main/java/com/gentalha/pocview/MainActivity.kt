package com.gentalha.pocview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gentalha.pocview.databinding.ActivityMainBinding
import com.gentalha.pocview.ConfigScript.scripts
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val urlLoginPadrao = "192.168.15.1/instalador"
    private val urlLogin = "http://192.168.15.1/cgi-bin/sophia_index.cgi"
    private lateinit var binding: ActivityMainBinding

    // Define um CoroutineScope para lançar coroutines
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.webView.apply {
            show()
            settings.javaScriptEnabled = true
            loadUrl(urlLogin)
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    println("THG_LOG current url-> $url")

                    if (url == urlLogin || url == urlLoginPadrao) {
                        coroutineScope.launch {
                            scripts.forEach { script ->
                                println("THG_LOG --> $script")
                                evaluateJavascript(script.script) { result ->
                                    println("THG_LOG --> $result")
                                }
                                delay(script.delayTime)  // Adicione um atraso de 2 segundos
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

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()  // Cancela as coroutines quando a atividade é destruída
    }
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}
