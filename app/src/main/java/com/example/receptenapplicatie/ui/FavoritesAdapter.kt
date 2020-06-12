package com.example.receptenapplicatie.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.receptenapplicatie.R
import com.example.receptenapplicatie.database.RecipeFavoritesRepository
import com.example.receptenapplicatie.model.RecipeEntity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_favorite.view.*
import kotlinx.android.synthetic.main.item_favorite.view.ivFavoriteStar
import kotlinx.android.synthetic.main.item_recipe.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesAdapter(private val favorites: ArrayList<RecipeEntity>) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var recipeFavoritesRepository: RecipeFavoritesRepository

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(recipeEntity: RecipeEntity) {
            itemView.tvFavoriteTitle.text = recipeEntity.title
            itemView.tvFavoriteTijd.text = context.getString(R.string.mintijd, recipeEntity.readyInMinutes)
            Glide.with(context).load(recipeEntity.image).into(itemView.ivFavoriteImage)
            Glide.with(context).load(R.drawable.ic_star_yellow_24dp).into(itemView.ivFavoriteStar)

            itemView.ivFavoriteStar.setOnClickListener {
                Snackbar.make(it, "Favoriet verwijderd", Snackbar.LENGTH_LONG).show()
                mainScope.launch {
                    withContext(Dispatchers.IO) {
                        recipeFavoritesRepository = RecipeFavoritesRepository(context)
                        recipeFavoritesRepository.deleteFavorite(recipeEntity)
                    }
                }
            }
        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return favorites.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favorites[position])
    }


}