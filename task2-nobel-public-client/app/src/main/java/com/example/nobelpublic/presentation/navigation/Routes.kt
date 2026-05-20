package com.example.nobelpublic.presentation.navigation

object Routes {
    const val LIST = "list"
    const val DETAIL = "detail/{item}"
    fun detail(item: String) = "detail/$item"
}
