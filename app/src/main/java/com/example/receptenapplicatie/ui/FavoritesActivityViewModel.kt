package com.example.receptenapplicatie.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.receptenapplicatie.database.RecipeFavoritesRepository
import com.example.receptenapplicatie.model.Recipe
import com.example.receptenapplicatie.model.RecipeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val ioScope = CoroutineScope(Dispatchers.IO)
    private val recipeFavoritesRepository = RecipeFavoritesRepository(application.applicationContext)

    fun insertFavorite(recipeEntity: RecipeEntity) {
        ioScope.launch {
            recipeFavoritesRepository.insertFavorite(recipeEntity)
        }
    }

    fun deleteFavorite(recipeEntity: RecipeEntity) {
        ioScope.launch {
            withContext(Dispatchers.IO) {
                recipeFavoritesRepository.deleteFavorite(recipeEntity)
            }
        }
    }

    fun deleteAllFavorites() {
        ioScope.launch {
            recipeFavoritesRepository.deleteAllFavorites()
        }
    }

}