package com.example.receptenapplicatie.database

import com.example.receptenapplicatie.BuildConfig
import com.example.receptenapplicatie.model.RecipeDetailsList
import com.example.receptenapplicatie.model.RecipeList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeApiService {

    /**
     * Return recipes from API
     */
    @GET("https://api.spoonacular.com/recipes/search?apiKey=${BuildConfig.RECIPES_API_KEY}&number=10")
    fun getRecipes(): Call<RecipeList>

    /**
     * Return specific recipe information
     */
    @GET("https://api.spoonacular.com/recipes/{id}/information")
    fun getRecipeDetails(@Path("id") id: String) : Call<RecipeDetailsList>
}