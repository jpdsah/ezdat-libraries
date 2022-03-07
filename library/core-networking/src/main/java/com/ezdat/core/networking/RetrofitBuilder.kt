package com.ezdat.core.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit

object RetrofitBuilder {
    operator fun invoke(baseUrl: String, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(httpClient).build()
    }
}