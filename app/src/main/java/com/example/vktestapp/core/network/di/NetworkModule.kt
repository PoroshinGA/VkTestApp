package com.example.vktestapp.core.network.di

import com.example.vktestapp.core.domain.GetProductsInfoUseCase
import com.example.vktestapp.core.network.ProductsService
import com.example.vktestapp.core.network.ProductsServiceImpl
import com.example.vktestapp.core.network.usecase.GetProductsInfoUseCaseImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalSerializationApi::class)
val provideNetworkModule = module {
    single(named("network")) {
        Json { ignoreUnknownKeys = true }
    }

    single {
        OkHttp.create()
    }

    single {
        val json = get<Json>(named("network"))
        val engine = get<HttpClientEngine>()

        HttpClient(engine) {

            install(ContentNegotiation) {
                json(
                    json = Json {
                        explicitNulls = false
                        ignoreUnknownKeys = true
                    }
                )
            }

            install(HttpTimeout) {
                connectTimeoutMillis = 20.seconds.inWholeMilliseconds
                requestTimeoutMillis = 60.seconds.inWholeMilliseconds
                socketTimeoutMillis = 20.seconds.inWholeMilliseconds
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.INFO
            }
        }
    }

    single<ProductsService> {
        ProductsServiceImpl(client = get())
    }

    single<GetProductsInfoUseCase> {
        GetProductsInfoUseCaseImpl(service = get())
    }
}
