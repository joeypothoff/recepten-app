package com.example.receptenapplicatie.database

import android.content.Context
import com.example.receptenapplicatie.model.RecipeEntity

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
}