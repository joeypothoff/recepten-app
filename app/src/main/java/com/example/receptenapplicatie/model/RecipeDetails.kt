package com.example.receptenapplicatie.model

import com.google.gson.annotations.SerializedName

data class RecipeDetails (
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("readyInMinutes") var readyInMinutes: String,
    @SerializedName("image") var image: String
)