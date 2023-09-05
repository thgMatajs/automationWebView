package com.gentalha.pocview

import com.gentalha.pocview.model.Script
import com.gentalha.pocview.model.ScriptModel

object ConfigScript {
    private const val INPUT_LOGIN_USER_SCRIPT = """
       element = document.getElementById("basefrm")
       child = element.contentWindow.document
       elemento = document.evaluate('//*[@id="Loginuser"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
       inp = child.getElementById("Loginuser")

// Verifique se o elemento foi encontrado antes de preencher o valor
if (elemento) {
    // Preencha o valor do elemento com "support"
    elemento.value = "support";
    "Elemento localizado pelo XPath."
} else {
    if (inp) {
        inp.value = "support";
        "Elemento localizado pelo ID."
    } else {
        "Erro 1 - Configuração do SLID.";
    }
}
    """
    // Adicione um atraso aqui antes de executar o próximo da fila


    private const val INPUT_LOGIN_PASSWORD_SCRIPT = """ 
    element = document.getElementById("basefrm")
    child = element.contentWindow.document
    input = document.evaluate('//*[@id="LoginPassword"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
    input_id = child.getElementById("LoginPassword") 

    // Verifique se o elemento foi encontrado antes de preencher o valor
    if (input) {
        // Preencha o valor do elemento com "support"
        input.value = "balbtt43";
        "Elemento localizado pelo XPath."
    } else {
        if (input_id) {
            input_id.value = "balbtt43";
            "Elemento localizado pelo ID."
        } else {
            "Erro 2 - Configuração do SLID.";
        }
    }

    """

    const val CLICK_LOGIN_BTN = """ 
        element = document.getElementById("basefrm")
        child = element.contentWindow.document
        btn = document.evaluate('//*[@id="acceptLogin"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
        btnClick = child.getElementById("acceptLogin")
    
    if (btn) {
        // Preencha o valor do elemento com "support"
        btn.click();
        "Elemento localizado pelo XPath."
    } else {
        if (btnClick) {
            btnClick.click();
            "Elemento localizado pelo ID."
        } else {
            "Erro 3 - Configuração do SLID.";
        }
    }

    """

    private const val INSERT_SLID = """ 
    slid = document.evaluate('//*[@id="gponPassword"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;

    element = document.getElementById("basefrm")
    child = element.contentWindow.document
    slid_id = child.getElementById("gponPassword")  

    if (slid) {
        // Preencha o valor do elemento com "support"
        slid.value = "123456789";
        "Elemento localizado pelo XPath."
    } else {
        if (slid_id) {
            slid_id.value = "123456789";
            "Elemento localizado pelo ID."
        } else {
            "Erro 4 - Configuração do SLID.";
        }
    }

    """

    private const val APPLY_SLID = """
    btn = document.evaluate('//*[@id="install_wiz"]/table[2]/tbody/tr[3]/td/a[1]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
    element = document.getElementById("basefrm")
    child = element.contentWindow.document
    btn_apply = child.getElementsByClassName("btn-default-orange-small right")[1]
    
    if (btn) {
        // Preencha o valor do elemento com "support"
        btn.click();
        "Elemento localizado pelo XPath."
    } else {
        if (btn_apply) {
            btn_apply.click();
            "Elemento localizado pelo ID."
        } else {
            "Erro 5 - Configuração do SLID.";
        }
    }

    """


    val scripts: List<ScriptModel>
        get() = listOf(
            ScriptModel(Script.SLID, INPUT_LOGIN_USER_SCRIPT, 1000),
            ScriptModel(Script.SLID, INPUT_LOGIN_PASSWORD_SCRIPT, 1000),
            ScriptModel(Script.SLID, CLICK_LOGIN_BTN, 1000),
            ScriptModel(Script.SLID, INSERT_SLID, 1000),
            ScriptModel(Script.SLID, APPLY_SLID, 1000)
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