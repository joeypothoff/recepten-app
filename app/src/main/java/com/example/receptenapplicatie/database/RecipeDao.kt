package com.example.receptenapplicatie.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.receptenapplicatie.model.RecipeEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM receptenFavorites")
    suspend fun getAllFavorites(): List<RecipeEntity>

    @Insert
    suspend fun insertFavorite(recipeEntity: RecipeEntity)

    @Delete
    suspend fun deleteFavorite(recipeEntity: RecipeEntity)
}