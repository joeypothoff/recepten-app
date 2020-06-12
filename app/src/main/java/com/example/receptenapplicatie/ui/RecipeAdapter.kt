package com.example.receptenapplicatie.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.receptenapplicatie.R
import com.example.receptenapplicatie.database.RecipeFavoritesRepository
import com.example.receptenapplicatie.model.Recipe
import com.example.receptenapplicatie.model.RecipeEntity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.item_recipe.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeAdapter (private val recipes: ArrayList<Recipe>, private val onClick: (Recipe) -> Unit): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private lateinit var context: Context
    private val mainScope = CoroutineScope(Dispatchers.Main)
    private lateinit var recipeFavoritesRepository: RecipeFavoritesRepository

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onClick(recipes[adapterPosition])
            }
        }

        fun bind(recipe : Recipe) {
            itemView.tvTitle.text = recipe.title
            itemView.tvTijd.text = context.getString(R.string.mintijd, recipe.readyInMinutes)
            Glide.with(context).load(recipe.getRecipeImage()).into(itemView.ivRecipeImage)
            Glide.with(context).load(R.drawable.ic_star_border_yellow_24dp).into(itemView.ivFavoriteStar)

            itemView.ivFavoriteStar.setOnClickListener {
                Snackbar.make(it, "Favoriet toegevoegd", Snackbar.LENGTH_LONG).show()
                mainScope.launch {
                    withContext(Dispatchers.IO) {
                        recipeFavoritesRepository = RecipeFavoritesRepository(context)
                        recipeFavoritesRepository.insertFavorite(
                            RecipeEntity(
                                recipe.id,
                                recipe.title,
                                recipe.readyInMinutes,
                                recipe.getRecipeImage()
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(recipes[position])
    }
}