package com.example.vktestapp.core.model

import kotlinx.serialization.Serializable

/**
 * Data class representing a response containing a list of products, along with additional information
 *
 * @param [products] The list of [ProductInfo] representing the products in the response
 * @param [total] The total number of products available
 * @param [scip] An integer value representing a specific attribute in the response
 * @param [limiter] An integer value representing another attribute in the response
 */
@Serializable
data class Response(
    val products: List<ProductInfo>,
    val total: Int,
    val scip: Int,
    val limiter: Int
)
