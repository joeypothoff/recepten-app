package com.example.receptenapplicatie.model

import com.google.gson.annotations.SerializedName

data class RecipeList (
    @SerializedName("results") val results : List<Recipe>
)