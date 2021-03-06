package com.nishanth.foody.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nishanth.foody.models.FoodRecipe
import com.nishanth.foody.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id:Int=0
}