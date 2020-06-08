package com.example.receptenapplicatie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.receptenapplicatie.model.RecipeEntity

/*
 * Creation of a database class
 */
@Database(entities = [RecipeEntity::class], version = 2, exportSchema = false)
abstract class RecipeRoomDatabase : RoomDatabase() {

    // get database access object of Recept
    abstract fun recipeDao(): RecipeDao

    // make sure we can use function outside this class
    companion object {
        private const val DATABASE_NAME = "RECEPT_DATABASE"

        @Volatile
        private var recipeRoomDatabaseInstance: RecipeRoomDatabase? = null

        fun getDatabase(context: Context): RecipeRoomDatabase? {
            if (recipeRoomDatabaseInstance == null) {
                synchronized(RecipeRoomDatabase::class.java) {
                    if (recipeRoomDatabaseInstance == null) {
                        recipeRoomDatabaseInstance = Room.databaseBuilder(
                            context.applicationContext,
                            RecipeRoomDatabase::class.java, DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return recipeRoomDatabaseInstance
        }
    }

}