package com.example.vktestapp.core.network

import android.util.Log
import com.example.vktestapp.core.model.Response
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface ProductsService {
    suspend fun products(): Result<Response>
}

class ProductsServiceImpl(
    private val client: HttpClient = HttpClient(OkHttp),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ProductsService {
    override suspend fun products(): Result<Response> = withContext(dispatcher) {
        return@withContext try {
            client.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "dummyjson.com"
                    contentType(ContentType.Application.Json)
                    path("products")
                }
            }.let { response ->
                Log.d("ProductService", response.body())
                Result.success(value = response.body<Response>())
            }
        } catch (exception: Exception) {
            Log.e("ProductService", "Error: $exception")
            Result.failure(exception = exception)
        }
    }
}