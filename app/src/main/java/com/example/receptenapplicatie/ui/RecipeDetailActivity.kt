package com.example.receptenapplicatie.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.receptenapplicatie.R
import com.example.receptenapplicatie.database.RecipeRepository
import com.example.receptenapplicatie.model.Recipe
import com.example.receptenapplicatie.model.RecipeDetails
import com.example.receptenapplicatie.model.RecipeDetailsList
import com.example.receptenapplicatie.model.RecipeList
import kotlinx.android.synthetic.main.content_recipe.*

class RecipeDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeDetailActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        supportActionBar?.title = "Recept"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {
        val recipe = intent.getParcelableExtra(MainActivity.EXTRA_RECIPE) as Recipe

        viewModel = ViewModelProvider(this).get(RecipeDetailActivityViewModel::class.java)

        Glide.with(this).load(recipe.getRecipeImage()).into(ivRecipePic)
        tvTitle.text = recipe.title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}