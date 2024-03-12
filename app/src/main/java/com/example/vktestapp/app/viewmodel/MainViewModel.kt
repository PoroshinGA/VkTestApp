package com.example.vktestapp.app.viewmodel

import android.media.audiofx.DynamicsProcessing.Limiter
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

    private val _mainScreenModelState: MutableStateFlow<List<ProductShortCard>> =
        MutableStateFlow(mutableListOf())

    var skipCount = 0
    var total = 0

    fun request(): List<ProductShortCard> {
        viewModelScope.launch {
            getProductsInfoUseCase.invoke(skipCount).onSuccess { response ->
                _mainScreenModelState.update {
                    response.let { response ->
                        total = response.total ?: 0
                        response.products!!.map { productInfo ->
                            ProductShortCard(
                                id = productInfo.id,
                                title = productInfo.title,
                                description = productInfo.description.take(30) + " ...",
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
        return _mainScreenModelState.value
    }
}
