package org.marproject.currencyconversion.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl("https://api.exchangeratesapi.io/")
    .build()

interface ApiService {
    @GET("latest")
    suspend fun getData(): Currency
}

object ApiCurrency {
    val retrofitService: ApiService = retrofit.create(ApiService::class.java)
}