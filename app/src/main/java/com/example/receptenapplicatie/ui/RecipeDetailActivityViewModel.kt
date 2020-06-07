package com.example.receptenapplicatie.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.receptenapplicatie.database.RecipeRepository
import com.example.receptenapplicatie.model.RecipeDetailsList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipeDetailActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository()
    val recipes = MutableLiveData<RecipeDetailsList>()
    val error = MutableLiveData<String>()

    fun getRecipeDetailsList(id: String) {
        recipeRepository.getRecipeDetails(id).enqueue(object : Callback<RecipeDetailsList> {
            override fun onResponse(call: Call<RecipeDetailsList>, response: Response<RecipeDetailsList>) {
                if (response.isSuccessful) recipes.value = response.body()
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<RecipeDetailsList>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}