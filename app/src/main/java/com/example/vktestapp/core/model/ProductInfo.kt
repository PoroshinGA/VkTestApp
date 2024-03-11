package com.example.vktestapp.core.model

import kotlinx.serialization.Serializable

/**
 * Data class representing product information
 *
 * @param [id] unique identifier of the product
 * @param [title] title or name of the product
 * @param [description] detailed description of the product
 * @param [price] price of the product
 * @param [discountPercentage] discount percentage applied to the product
 * @param [rating] rating of the product
 * @param [stock] available stock quantity of the product
 * @param [brand] brand of the product
 * @param [category] category to which the product belongs
 * @param [thumbnail] URL or path to the product's thumbnail image
 * @param [images] list of URLs or paths to additional images of the product
 */
@Serializable
data class ProductInfo(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)
