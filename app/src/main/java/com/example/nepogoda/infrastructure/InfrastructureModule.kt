package com.example.nepogoda.infrastructure

import android.util.Log
import com.example.nepogoda.BuildConfig
import com.example.nepogoda.infrastructure.sources.remote.WeatherRemoteSource
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Duration

val InfrastructureModule = module {

    single {
        Moshi.Builder().build()
    }

    single {
        OkHttpClient.Builder()
            .connectTimeout(Duration.ofSeconds(20))
            .addInterceptor {chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url()

                // Dodajemy parametr "appid" z kluczem API do żądania HTTP
                val url = originalUrl.newBuilder()
                    .addQueryParameter("appid", BuildConfig.API_KEY)
                    .build()

                val requestBuilder = originalRequest.newBuilder().url(url)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor {chain ->
                val request = chain.request()
                if(BuildConfig.DEBUG) {
                    Log.d("Request: ", request.url().url().toString())
                }
                chain.proceed(request)
            }
            .readTimeout(Duration.ofSeconds(20))
            .build()
    }

    factory { (endPoint: String) ->
        Retrofit.Builder().baseUrl(BuildConfig.API_URL + endPoint).client(get())
            .addConverterFactory(MoshiConverterFactory.create(get())).build()
    }


    single {
        val retrofit: Retrofit = get { parametersOf("data/2.5/") }
        retrofit.create(WeatherRemoteSource::class.java)
    }
}