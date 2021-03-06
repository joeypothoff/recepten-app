package com.example.receptenapplicatie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe (
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("readyInMinutes") var readyInMinutes: String,
    @SerializedName("image") var image: String
) : Parcelable {

    fun getRecipeImage(): String {
        return "https://spoonacular.com/recipeImages/$image"
    }

}