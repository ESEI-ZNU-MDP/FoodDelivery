package com.fooddelivery.entities

data class Restaurant(
    val name: String,
    val imageResId: Int,
    val description: String,
    val menuItems: List<MenuItem>
)
