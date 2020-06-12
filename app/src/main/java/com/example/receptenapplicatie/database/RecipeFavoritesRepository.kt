package com.example.receptenapplicatie.database

import android.content.Context
import com.example.receptenapplicatie.model.RecipeEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class RecipeFavoritesRepository(context: Context) {

    private var recipeDao: RecipeDao

    init {
        val recipeRoomDatabase = RecipeRoomDatabase.getDatabase(context)
        recipeDao = recipeRoomDatabase!!.recipeDao()
    }

    suspend fun getAllFavorites(): List<RecipeEntity> {
        return recipeDao.getAllFavorites()
    }

    suspend fun insertFavorite(recipeEntity: RecipeEntity) {
        recipeDao.insertFavorite(recipeEntity)
    }

    suspend fun deleteFavorite(recipeEntity: RecipeEntity) {
        recipeDao.deleteFavorite(recipeEntity)
    }

    suspend fun deleteAllFavorites() {
        return recipeDao.deleteAllFavorites()
    }
}