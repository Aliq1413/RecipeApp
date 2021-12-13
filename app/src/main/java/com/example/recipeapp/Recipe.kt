package com.example.recipeapp
import com.google.gson.annotations.SerializedName
class Recipes : ArrayList<RecipeItem>()
data class RecipeItem(
    @SerializedName("title")
    val title: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("ingredient")
    val ingredient: String,
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("pk")
    val pK: Int
)
