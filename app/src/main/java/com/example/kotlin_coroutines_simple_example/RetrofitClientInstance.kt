package com.example.kotlin_coroutines_simple_example


import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


class RetrofitClientInstance {
    companion object {
        private  val BASE_URL = "https://cchat.in/SampleAPI/"
        private var retrofit: Retrofit? = null
        val retrofitInstance: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
                return retrofit
            }
    }
}

interface GetDataService {
    @get:GET("getMessage1.php")
    val getUrl1: Call<KotlinDataClass?>?

    @get:GET("getMessage2.php")
    val getUrl2: Call<KotlinDataClass?>?
}