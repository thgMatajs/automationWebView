package com.gentalha.pocview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.ValueCallback
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gentalha.pocview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupWebView()
    }
    private val routerLoginScript = """
        var elemento = document.evaluate('//*[@id="Loginuser"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;

// Verifique se o elemento foi encontrado antes de preencher o valor
if (elemento) {
    // Preencha o valor do elemento com "support"
    elemento.value = "support";
    "Preenchido user name com sucesso.";
} else {
    "Elemento não encontrado com o XPath fornecido.";
}
    """

    private val routerLoginPasswordScript = """
        var elemento = document.evaluate('//*[@id="LoginPassword"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;

// Verifique se o elemento foi encontrado antes de preencher o valor
if (elemento) {
    elemento.value = "d2d54ca4";
    "Preenchido LoginPassword com sucesso.";
} else {
    "Elemento não encontrado com o XPath fornecido.";
}
    """

    private val routerLoginClick = """
        
        var elemento = document.evaluate('//*[@id="Login_ID"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;

// Verifique se o elemento foi encontrado antes de preencher o valor
if (elemento) {
    elemento.click();
    "Botao clicado";
} else {
    "Elemento não encontrado com o XPath fornecido.";
}
    """

    private val xPathOrIdScript = """
        try {
    var xpath = '//*[@id="cc"]';
    var elemento = document.evaluate(xpath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;

    if (!elemento) {
        var id = 'Login_ID';
        elemento = document.getElementById(id); 
    }

    if (elemento) {
        elemento.click();
        "Clicado com sucesso.";
    } else {
        "Elemento não encontrado com XPath ou ID fornecidos.";
    }
} catch (error) {
    "Ocorreu um erro: " + error.message;
}
    """

   private val scripts = listOf<String>(
        routerLoginScript,
        routerLoginPasswordScript,
       xPathOrIdScript
    )

    private val urlLoginPadrao = "192.168.15.1/padrao"
    private val urlLogin = "http://192.168.15.1/cgi-bin/login_advance.cgi"



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
                        scripts.forEach {script ->
                            println("THG_LOG --> $script")
                            evaluateJavascript(script) { result ->
                                println("THG_LOG --> $result")
                            }
                        }
                    } else {
                        Toast.makeText(this@MainActivity, "Etapa de LOGIN finalizada com SUCESSO!!", Toast.LENGTH_LONG).show()
                        binding.progress.hide()
                        binding.textView.show()
                    }
                }

            }

        }
    }

    private fun setupInput(xPath: String, value: String) = """
        
        ${println("THG_LOG xPath -> $xPath ")}
        
        try {
        var elemento = document.evaluate($xPath, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
        // Verifique se o elemento foi encontrado antes de preencher o valor
            if (elemento) {
                // Preencha o valor do elemento
                elemento.value = $value;
                return "Preenchido com sucesso.";
            } else {
                return "Elemento não encontrado com o XPath fornecido.";
            }
        } catch (error) {
        return "Ocorreu um erro: " + error.message;
    }
    """
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}