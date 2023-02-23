package com.cryptoxin.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AllInterceptor @Inject constructor() :Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
       val request=chain.request().newBuilder()
        request.addHeader("Content-Type","application/json")


        return chain.proceed(request.build())
    }
}