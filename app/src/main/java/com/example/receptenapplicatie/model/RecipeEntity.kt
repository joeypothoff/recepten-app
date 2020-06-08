package com.example.receptenapplicatie.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "receptenFavorites")
data class RecipeEntity (

    @ColumnInfo(name = "recipeId")
    var recipeId: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "readyInMinutes")
    var readyInMinutes: String,

    @ColumnInfo(name = "image")
    var image: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

) : Parcelable