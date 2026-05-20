package com.example.photocatalog.presentation.navigation

object Routes {
    const val LIST = "list"
    const val DETAIL = "detail/{photoJson}"

    fun detail(photoJson: String): String = "detail/$photoJson"
}
