package kr.co.naveropenapi

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private const val BASE_URL = "https://openapi.naver.com/v1/search/"

    val client = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .connectTimeout(100, TimeUnit.MINUTES)
            .readTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getInstance(): NaverAPI = retrofit.create(NaverAPI::class.java)

    private fun httpLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor { message -> Log.e("MyOkHttpClient :", message + "") }
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }
}