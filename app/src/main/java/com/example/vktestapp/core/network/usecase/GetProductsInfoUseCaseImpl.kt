package com.example.vktestapp.core.network.usecase

import com.example.vktestapp.core.domain.GetProductsInfoUseCase
import com.example.vktestapp.core.model.Response
import com.example.vktestapp.core.network.ProductsService

class GetProductsInfoUseCaseImpl(private val service: ProductsService): GetProductsInfoUseCase {
    override suspend fun invoke(skip: Int): Result<Response> =
        service.products(skip)
}
