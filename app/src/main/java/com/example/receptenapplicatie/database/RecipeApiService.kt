package com.example.receptenapplicatie.database

import com.example.receptenapplicatie.BuildConfig
import com.example.receptenapplicatie.model.RecipeList
import retrofit2.Call
import retrofit2.http.GET

interface RecipeApiService {

    /**
     * Return recipes from API
     */
    @GET("https://api.spoonacular.com/recipes/search?apiKey=${BuildConfig.RECIPES_API_KEY}&number=10")
    fun getRecipes(): Call<RecipeList>
}