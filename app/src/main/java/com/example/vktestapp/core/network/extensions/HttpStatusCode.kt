/*
 * Copyright (c) 2023 Lanit-Tercom, LLC.
 */

package com.example.vktestapp.core.network.extensions

import io.ktor.http.HttpStatusCode

/**
 * Checks if the HTTP status code is a redirection (3xx)
 *
 * @return true if the status code is in the range 300..399, false otherwise
 */
internal fun HttpStatusCode.isRedirection() = value in 300..399

/**
 * Checks if the HTTP status code is a client error (4xx)
 *
 * @return true if the status code is in the range 400..499, false otherwise
 */
internal fun HttpStatusCode.isClientError() = value in 400..499

/**
 * Checks if the HTTP status code is a server error (5xx)
 *
 * @return true if the status code is in the range 500..599, false otherwise
 */
internal fun HttpStatusCode.isServerError() = value in 500..599
