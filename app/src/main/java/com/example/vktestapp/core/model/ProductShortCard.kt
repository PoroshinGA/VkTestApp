package com.example.vktestapp.core.model

import androidx.compose.runtime.Immutable

/**
 * Data class representing product short card information
 *
 * @param [id] unique identifier of the product
 * @param [title] title or name of the product
 * @param [description] detailed description of the product
 * @param [price] price of the product
 * @param [discountPercentage] discount percentage applied to the product
 * @param [thumbnail] URL or path to the product's thumbnail image
 */
@Immutable
data class ProductShortCard(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val thumbnail: String
)
