package com.fooddelivery.entities

import java.io.Serializable

data class MenuItem(val name: String, val price: Double, val imageResId: Int): Serializable
