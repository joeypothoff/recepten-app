package com.example.receptenapplicatie.database

import com.example.receptenapplicatie.model.RecipeDetailsList
import com.example.receptenapplicatie.model.RecipeList
import retrofit2.Call

class RecipeRepository {

    private val recipeApi: RecipeApiService = RecipeApi.createApi()

    fun getRecipes(): Call<RecipeList> {
        return recipeApi.getRecipes()
    }

    fun getRecipeDetails(id: String): Call<RecipeDetailsList> {
        return recipeApi.getRecipeDetails(id)
    }
}