package com.example.receptenapplicatie.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.receptenapplicatie.R
import com.example.receptenapplicatie.model.Recipe
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeAdapter (private val recipes: ArrayList<Recipe>, private val onClick: (Recipe) -> Unit): RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    private lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onClick(recipes[adapterPosition])
            }
        }

        fun bind(recipe : Recipe) {
            itemView.tvTitle.text = recipe.title
            itemView.tvPerson.text = recipe.username
            Glide.with(context).load("https://s3.amazonaws.com/appsdeveloperblog/Micky.jpg").into(itemView.ivRecipeImage)
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