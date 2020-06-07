package com.example.receptenapplicatie.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.receptenapplicatie.R
import com.example.receptenapplicatie.model.Recipe
import com.example.receptenapplicatie.model.RecipeList

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        supportActionBar?.title = "Recept"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {
        val recipe = intent.getParcelableExtra(MainActivity.EXTRA_RECIPE) as Recipe
        val recipeDetails =
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}