package com.gentalha.pocview

object LoginScript {
    private const val INPUT_LOGIN_USER_SCRIPT = """
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

    private const val INPUT_LOGIN_PASSWORD_SCRIPT = """
        var elemento = document.evaluate('//*[@id="LoginPassword"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;

// Verifique se o elemento foi encontrado antes de preencher o valor
if (elemento) {
    elemento.value = "d2d54ca4";
    "Preenchido LoginPassword com sucesso.";
} else {
    "Elemento não encontrado com o XPath fornecido.";
}
    """

    private const val CLICK_LOGIN_BTN_BY_ID_SCRIPT = """
        
        var elemento = document.evaluate('//*[@id="Login_ID"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;

// Verifique se o elemento foi encontrado antes de preencher o valor
if (elemento) {
    elemento.click();
    "Botao clicado";
} else {
    "Elemento não encontrado com o XPath fornecido.";
}
    """

    private const val CLICK_LOGIN_BTN_BY_ID_OR_XPATH_SCRIPT = """
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

    val scripts: List<String>
        get() = listOf(
            INPUT_LOGIN_USER_SCRIPT,
            INPUT_LOGIN_PASSWORD_SCRIPT,
            CLICK_LOGIN_BTN_BY_ID_OR_XPATH_SCRIPT
        )

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