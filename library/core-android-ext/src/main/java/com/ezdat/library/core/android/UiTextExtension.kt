package com.ezdat.library.core.android

import android.content.Context
import com.ezdat.library.core.UiText

fun UiText.asString(context: Context): String {
    return when (this) {
        is UiText.DynamicString -> this.text
        is UiText.StringResource -> context.getString(this.id)
    }
}