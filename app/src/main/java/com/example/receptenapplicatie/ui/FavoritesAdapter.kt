package com.example.receptenapplicatie.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.receptenapplicatie.R
import com.example.receptenapplicatie.model.RecipeEntity
import kotlinx.android.synthetic.main.item_favorite.view.*

class FavoritesAdapter(private val favorites: ArrayList<RecipeEntity>) : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(recipeEntity: RecipeEntity) {
            itemView.tvFavoriteTitle.text = recipeEntity.title
            itemView.tvFavoriteTijd.text = context.getString(R.string.mintijd, recipeEntity.readyInMinutes)
            Glide.with(context).load(recipeEntity.image).into(itemView.ivFavoriteImage)
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