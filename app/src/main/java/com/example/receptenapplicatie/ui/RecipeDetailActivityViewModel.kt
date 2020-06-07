package com.example.receptenapplicatie.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.receptenapplicatie.database.RecipeRepository
import com.example.receptenapplicatie.model.RecipeDetails
import com.example.receptenapplicatie.model.RecipeDetailsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeDetailActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository()
    val recipes = MutableLiveData<RecipeDetails>()
    val error = MutableLiveData<String>()

    fun getRecipeDetailsList(id: Int) {
        recipeRepository.getRecipeDetails(id).enqueue(object : Callback<RecipeDetails> {
            override fun onResponse(call: Call<RecipeDetails>, response: Response<RecipeDetails>) {
                if (response.isSuccessful) recipes.value = response.body()
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<RecipeDetails>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}