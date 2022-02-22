package com.example.interviewtask.network

import com.example.interviewtask.data.model.MainResult
import com.example.interviewtask.utils.Constant.BASEURL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("users")
    suspend fun getListApi(@Query("page") pages : Int): MainResult


    companion object {

        fun createService(
            httpClient: HttpClientBuilderFactory
        ): Services {

            val okHttpClient = httpClient.create().build()

            return Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Services::class.java)
        }
    }
}