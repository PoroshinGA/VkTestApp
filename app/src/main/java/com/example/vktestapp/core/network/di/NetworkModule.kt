package com.example.vktestapp.core.network.di

import com.example.vktestapp.core.domain.GetProductsInfoUseCase
import com.example.vktestapp.core.network.ProductsService
import com.example.vktestapp.core.network.ProductsServiceImpl
import com.example.vktestapp.core.network.usecase.GetProductsInfoUseCaseImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.time.Duration.Companion.seconds

val provideNetworkModule = module {
    single(named("network")) {
        Json { ignoreUnknownKeys = true }
    }

    single {
        OkHttp.create()
    }

    single {
        val engine = get<HttpClientEngine>()

        HttpClient(engine) {
            install(HttpTimeout) {
                connectTimeoutMillis = 20.seconds.inWholeMilliseconds
                requestTimeoutMillis = 60.seconds.inWholeMilliseconds
                socketTimeoutMillis = 20.seconds.inWholeMilliseconds
            }
        }
    }

    single<ProductsService> {
        ProductsServiceImpl()
    }

    single<GetProductsInfoUseCase> {
        GetProductsInfoUseCaseImpl(service = get())
    }
}
