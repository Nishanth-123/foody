package com.nishanth.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nishanth.foody.databinding.RecipesRowLayoutBinding
import com.nishanth.foody.models.FoodRecipe
import com.nishanth.foody.util.RecipesDiffUtil

class RecipesAdapter: RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private var recipes = emptyList<Result>()
    class MyViewHolder(private val binding: RecipesRowLayoutBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(result:Result){
            binding.result=result
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup):MyViewHolder{
                val layoutInflator=LayoutInflater.from(parent.context)
                val recipesRowLayoutBinding=RecipesRowLayoutBinding.inflate(layoutInflator, parent, false)
                return MyViewHolder(recipesRowLayoutBinding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentRecepe=recipes[position]
        holder.bind(currentRecepe)

    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    fun setData(newData:FoodRecipe){
        val recipesDiffUtil=RecipesDiffUtil(recipes, newData.results)
        val diffUtilResult=DiffUtil.calculateDiff(recipesDiffUtil)
        recipes=newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }

}