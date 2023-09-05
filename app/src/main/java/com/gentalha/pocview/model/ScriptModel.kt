package com.gentalha.pocview.model

data class ScriptModel(
    val name: Script,
    val script: String,
    val delayTime: Long
)

enum class Script {
    SLID
}
