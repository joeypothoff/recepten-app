package com.example.receptenapplicatie.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe (
    @SerializedName("id") var id: Int,
    @SerializedName("title") var title: String,
    @SerializedName("readyInMinutes") var readyInMinutes: String,
    @SerializedName("username") var username: String,
    @SerializedName("totalminutes") var totalminutes: String,
    @SerializedName("instructions") var instructions: String,
    @SerializedName("image") var image: String
) : Parcelable {

    fun getRecipeImage(): String {
        return "https://spoonacular.com/recipeImages/$image"
    }

}