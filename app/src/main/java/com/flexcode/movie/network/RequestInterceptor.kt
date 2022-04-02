package com.flexcode.movie.network

import com.flexcode.movie.util.Constants.API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest  = chain.request()
        val httpUrl = originalRequest.url()
        val url = httpUrl.newBuilder()
            .addQueryParameter("api_key",API_KEY)
            .build()
        val request = originalRequest.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}