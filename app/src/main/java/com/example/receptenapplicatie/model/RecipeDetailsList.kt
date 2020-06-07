package com.example.receptenapplicatie.model

import com.google.gson.annotations.SerializedName

data class RecipeDetailsList (
    @SerializedName("results") val results : List<RecipeDetails>
)