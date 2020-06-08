package com.example.receptenapplicatie.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.receptenapplicatie.R
import com.example.receptenapplicatie.database.RecipeFavoritesRepository
import com.example.receptenapplicatie.model.RecipeEntity
import kotlinx.android.synthetic.main.content_favorites.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesActivity : AppCompatActivity() {

    private lateinit var viewModel: RecipeDetailActivityViewModel
    private val favorites = arrayListOf<RecipeEntity>()
    private val favoritesAdapter = FavoritesAdapter(favorites)
    private lateinit var recipeFavoritesRepository: RecipeFavoritesRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_favorites)

        supportActionBar?.title = "Favorieten"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recipeFavoritesRepository = RecipeFavoritesRepository(this)

        initViews()
    }

    private fun initViews() {
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        rvFavorites.layoutManager = gridLayoutManager
        rvFavorites.adapter = favoritesAdapter

        rvFavorites.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                rvFavorites.viewTreeObserver.removeOnGlobalLayoutListener(this)
                gridLayoutManager.spanCount = calculateSpanCount()
                gridLayoutManager.requestLayout()
            }
        })

        createItemTouchHelper().attachToRecyclerView(rvFavorites)

        getFavoritesFromDatabase()
    }

    private fun getFavoritesFromDatabase() {
        CoroutineScope(Dispatchers.Main).launch {
            val favorites = withContext(Dispatchers.IO) {
                recipeFavoritesRepository.getAllFavorites()
            }
            this@FavoritesActivity.favorites.clear()
            this@FavoritesActivity.favorites.addAll(favorites)
            favoritesAdapter.notifyDataSetChanged()
        }
    }

    /**
     * Calculate the number of spans for the recycler view based on the recycler view width.
     * @return int number of spans.
     */
    private fun calculateSpanCount(): Int {
        val viewWidth = rvFavorites.measuredWidth
        val cardViewWidth = resources.getDimension(R.dimen.poster_width)
        val cardViewMargin = resources.getDimension(R.dimen.margin_medium)
        val spanCount = Math.floor((viewWidth / (cardViewWidth + cardViewMargin)).toDouble()).toInt()
        return if (spanCount >= 2) spanCount else 2
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
            }
        }
        return ItemTouchHelper(callback)
    }
}