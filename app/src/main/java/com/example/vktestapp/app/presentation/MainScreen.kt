package com.example.vktestapp.app.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vktestapp.app.viewmodel.MainViewModel
import com.example.vktestapp.core.ui.components.ProductCard


@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    var products by remember {
        mutableStateOf(
            viewModel.request()
        )
    }
    var buttonText by remember {
        mutableStateOf("Загрузить список товаров")
    }
    val lazyGridState: LazyGridState = rememberLazyGridState()

    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
        horizontalArrangement = Arrangement.Center,
        contentPadding = PaddingValues(16.dp),
        state = lazyGridState
    ) {
        items(products) { product ->
            ProductCard(
                model = product
            )
        }
        item {
            Button(
                onClick = {
                    if (viewModel.skipCount <= viewModel.total) {
                        buttonText = "Следующая страница"
                        products = viewModel.request()
                    } else {
                        buttonText = "Это весь список товаров"
                    }
                }
            ) {
                Text(text = buttonText)
            }
        }
    }
}
