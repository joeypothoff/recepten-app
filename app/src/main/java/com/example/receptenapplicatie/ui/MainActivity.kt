package com.example.receptenapplicatie.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.receptenapplicatie.R
import com.example.receptenapplicatie.model.Recipe
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private val recipes = arrayListOf<Recipe>()
    private val recipeAdapter = RecipeAdapter(recipes) { recipe -> onRecipeClick(recipe) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            startDetailActivity()
        }

        initViews()
    }

    private fun initViews() {
        rvRecepten.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rvRecepten.adapter = recipeAdapter
        recipes.add(
            Recipe(
                1000,
                "Recipe 1",
                "joey",
                "30 min",
                "Hier omen instructions",
                "https://via.placeholder.com/150"
            )
        )
        recipes.add(
            Recipe(
                1001,
                "Recipe 2",
                "joey",
                "30 min",
                "Hier omen instructions",
                "https://via.placeholder.com/150"
            )
        )
        recipeAdapter.notifyDataSetChanged()
    }

    private fun startDetailActivity() {
        val intent = Intent(this, RecipeDetailActivity::class.java)
        startActivity(intent)
    }

    private fun onRecipeClick(recipe: Recipe) {
        Snackbar.make(rvRecepten, "This recipe is: ${recipe.title}", Snackbar.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.home -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
