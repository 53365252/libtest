package com.allen.weather.app

import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

class MyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val defaultHeaders: Headers = mapOf<String, String>(
				HEADER_ACCEPT_LANGUAGE to Locale.getDefault().toLanguageTag(),
				HEADER_CONTENT_LANGUAGE to Locale.getDefault().toLanguageTag()
        ).toHeaders()

        val modifiedHeaders = request.headers.newBuilder().addAll(defaultHeaders).build()

        request = request.newBuilder()
            .headers(modifiedHeaders)
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val HEADER_ACCEPT_LANGUAGE = "Accept-Language"
        private const val HEADER_CONTENT_LANGUAGE = "Content-Language"
    }
}