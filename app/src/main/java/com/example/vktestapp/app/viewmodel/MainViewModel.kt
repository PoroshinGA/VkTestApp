package com.example.vktestapp.app.viewmodel

import androidx.lifecycle.ViewModel
import com.example.vktestapp.core.domain.GetProductsInfoUseCase
import com.example.vktestapp.core.model.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(private val getProductsInfoUseCase: GetProductsInfoUseCase) : ViewModel() {

    sealed class State {
        data object Init : State()
        data object Loading : State()
        data object Error : State()
        data object Success : State()
    }

    private val _mainScreenModelState: MutableStateFlow<Response> = MutableStateFlow(
        Response(
            products = null,
            total = null,
            skip = null,
            limit = null
        )
    )

    val mainScreenModelState: StateFlow<Response> = _mainScreenModelState.asStateFlow()

}