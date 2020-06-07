package com.example.receptenapplicatie.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewTreeObserver
import android.widget.GridLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.receptenapplicatie.R
import com.example.receptenapplicatie.model.Recipe
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private val recipes = arrayListOf<Recipe>()
    private val recipeAdapter = RecipeAdapter(recipes) {
            recipe -> onRecipeClick(recipe)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initViewModel()
    }

    private fun initViews() {
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rvRecepten.layoutManager = gridLayoutManager
        rvRecepten.adapter = recipeAdapter

        rvRecepten.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                rvRecepten.viewTreeObserver.removeOnGlobalLayoutListener(this)
                gridLayoutManager.spanCount = calculateSpanCount()
                gridLayoutManager.requestLayout()
            }
        })

        recipeAdapter.notifyDataSetChanged()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.recipes.observe(this, Observer {
            recipes.clear()
            it.results.forEach{
                recipes.add(it)
            }
            recipeAdapter.notifyDataSetChanged()
        })

        viewModel.getRecipeList()
    }

    /**
     * Calculate the number of spans for the recycler view based on the recycler view width.
     * @return int number of spans.
     */
    private fun calculateSpanCount(): Int {
        val viewWidth = rvRecepten.measuredWidth
        val cardViewWidth = resources.getDimension(R.dimen.poster_width)
        val cardViewMargin = resources.getDimension(R.dimen.margin_medium)
        val spanCount = Math.floor((viewWidth / (cardViewWidth + cardViewMargin)).toDouble()).toInt()
        return if (spanCount >= 2) spanCount else 2
    }

    private fun onRecipeClick(recipe: Recipe) {
        val intent = Intent(this, RecipeDetailActivity::class.java)
        intent.putExtra(EXTRA_RECIPE, recipe)
        startActivity(intent)
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

    companion object {
        const val EXTRA_RECIPE = "EXTRA_RECIPE"
    }
}
