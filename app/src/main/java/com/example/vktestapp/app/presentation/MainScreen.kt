package com.example.vktestapp.app.presentation

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import com.example.vktestapp.core.model.ProductShortCard
import com.example.vktestapp.core.ui.components.ProductCard

@Composable
fun MainScreen(products: List<ProductShortCard>) {
    LazyColumn {
        products.forEach { product ->
            item {
                key { product.id }
                ProductCard(model = product)
            }
        }
    }
}
