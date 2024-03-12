package com.example.vktestapp.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vktestapp.core.domain.GetProductsInfoUseCase
import com.example.vktestapp.core.model.ProductShortCard
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val getProductsInfoUseCase: GetProductsInfoUseCase
) : ViewModel() {

    private val _mainScreenModelState: MutableStateFlow<List<ProductShortCard>> = MutableStateFlow(
        listOf(
            ProductShortCard(
                id = 0,
                title = String(),
                description = String(),
                price = 0,
                discountPercentage = 0.0,
                thumbnail = String()
            )
        )
    )

    val mainScreenModelState: StateFlow<List<ProductShortCard>> =
        _mainScreenModelState.asStateFlow()

    private var skipCount = 0

    fun request(index: Int) {
        if (index % 8 == 0) {
            viewModelScope.launch {
                getProductsInfoUseCase.invoke(skipCount).onSuccess { response ->
                    _mainScreenModelState.update {
                        response.products!!.map { productInfo ->
                            ProductShortCard(
                                id = productInfo.id,
                                title = productInfo.title,
                                description = productInfo.description,
                                price = productInfo.price,
                                discountPercentage = productInfo.discountPercentage,
                                thumbnail = productInfo.thumbnail
                            )
                        }
                    }
                }
            }
            skipCount += 20
        }
    }
}
