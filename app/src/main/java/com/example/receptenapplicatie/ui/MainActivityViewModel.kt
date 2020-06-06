package com.example.receptenapplicatie.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.receptenapplicatie.database.RecipeRepository
import com.example.receptenapplicatie.model.RecipeList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository()
    val recipes = MutableLiveData<RecipeList>()
    val error = MutableLiveData<String>()

    fun getRecipeList() {
        recipeRepository.getRecipes().enqueue(object : Callback<RecipeList> {
            override fun onResponse(call: Call<RecipeList>, response: Response<RecipeList>) {
                if (response.isSuccessful) recipes.value = response.body()
                else error.value = "An error occurred: ${response.errorBody().toString()}"
            }

            override fun onFailure(call: Call<RecipeList>, t: Throwable) {
                error.value = t.message
            }
        })
    }
}