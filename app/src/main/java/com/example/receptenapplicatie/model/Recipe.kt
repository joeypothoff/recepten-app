package com.example.receptenapplicatie.model

import com.google.gson.annotations.SerializedName

data class Recipe (
    @SerializedName("recipeID") var recipeID: Int,
    @SerializedName("title") var title: String,
    @SerializedName("username") var username: String,
    @SerializedName("totalminutes") var totalminutes: String,
    @SerializedName("instructions") var instructions: String,
    @SerializedName("image") var image: String
)