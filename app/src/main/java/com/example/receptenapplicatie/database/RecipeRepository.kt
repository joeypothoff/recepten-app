package com.example.receptenapplicatie.database

import android.content.Context
import com.example.receptenapplicatie.model.RecipeDetails
import com.example.receptenapplicatie.model.RecipeEntity
import com.example.receptenapplicatie.model.RecipeList
import retrofit2.Call

class RecipeRepository() {

    private val recipeApi: RecipeApiService = RecipeApi.createApi()

    fun getRecipes(): Call<RecipeList> {
        return recipeApi.getRecipes()
    }

    fun getRecipeDetails(id: Int): Call<RecipeDetails> {
        return recipeApi.getRecipeDetails(id)
    }
}