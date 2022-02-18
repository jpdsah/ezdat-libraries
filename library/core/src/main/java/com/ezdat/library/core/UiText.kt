package com.ezdat.library.core

sealed class UiText {
    data class StringResource(val id: Int) : UiText()
    data class DynamicString(val text: String) : UiText()
}
