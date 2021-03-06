package com.nishanth.foody.data

import com.nishanth.foody.data.network.FoodRecipesApi
import com.nishanth.foody.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
){
    suspend fun getRecipes(queries:Map<String, String>):Response<FoodRecipe>{
        return foodRecipesApi.getRecipes(queries)
    }
}