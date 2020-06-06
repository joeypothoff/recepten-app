package com.example.receptenapplicatie.database

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecipeApi {
    companion object {
        private const val baseUrl = "https://api.spoonacular.com/recipes/search/"

        /**
         * @return [RecipeApiService] The service class off the retrofit client.
         */
        fun createApi(): RecipeApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            // Create the Retrofit instance
            val movieApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the Retrofit NumbersApiService
            return movieApi.create(RecipeApiService::class.java)
        }
    }
}