package com.allen.weather.extension

import retrofit2.HttpException
import java.net.HttpURLConnection

fun Throwable.isHttpUnauthorized(): Boolean =
    this is HttpException && this.code() == HttpURLConnection.HTTP_UNAUTHORIZED

fun Throwable.isHttpForbidden(): Boolean =
    this is HttpException && this.code() == HttpURLConnection.HTTP_FORBIDDEN

fun Throwable.isHttpBadRequest(): Boolean =
    this is HttpException && this.code() == HttpURLConnection.HTTP_BAD_REQUEST

fun Throwable.isHttpTooManyRequests(): Boolean =
    this is HttpException && this.code() == 429

fun Throwable.isHttpNotFound(): Boolean =
    this is HttpException && this.code() == HttpURLConnection.HTTP_NOT_FOUND

fun Throwable.isHttpNotAcceptable(): Boolean =
    this is HttpException && this.code() == HttpURLConnection.HTTP_NOT_ACCEPTABLE

fun Throwable.isNoSuchElement(): Boolean =
    this is NoSuchElementException