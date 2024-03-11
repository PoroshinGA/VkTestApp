package com.example.vktestapp.core.domain

import com.example.vktestapp.core.model.Response

/**
 * Interface representing the use case for retrieving product information
 *
 * This use case extends [UseCase] and provides a suspend operator function to invoke the use case
 *
 * @return [Result] of type [Response] representing the result of the use case
 */
interface GetProductsInfoUseCase: UseCase {
    suspend operator fun invoke(): Result<Response>
}
