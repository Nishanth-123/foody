package com.nishanth.foody.viewModels

import android.app.Application
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.nishanth.foody.data.DataStoreRepository
import com.nishanth.foody.util.Constants
import com.nishanth.foody.util.Constants.Companion.API_KEY
import com.nishanth.foody.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.nishanth.foody.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.nishanth.foody.util.Constants.Companion.DEFAULT_RECIPES_NUMBER
import com.nishanth.foody.util.Constants.Companion.QUERY_ADD_RECIPE_INFORMATION
import com.nishanth.foody.util.Constants.Companion.QUERY_API_KEY
import com.nishanth.foody.util.Constants.Companion.QUERY_DIET
import com.nishanth.foody.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.nishanth.foody.util.Constants.Companion.QUERY_NUMBER
import com.nishanth.foody.util.Constants.Companion.QUERY_TYPE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipesViewModel @ViewModelInject constructor(
    application: Application, private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE
    var netwrokStatus=false
    val readMealAndDietType = dataStoreRepository.readMealAndDietType

    fun saveMealAndDietType(mealType: String, mealTypeId: Int, dietType: String, dietTypeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietType(mealType, mealTypeId, dietType, dietTypeId)
        }
    }

    fun applyQueries(): HashMap<String, String> {

        viewModelScope.launch {
            readMealAndDietType.collect { value ->
                mealType = value.selectedMealType,
                dietType = value.selectedDietType
            }
        }

        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER.toString()
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

    fun showNetworkStatus(){
        if(!netwrokStatus){
            Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_SHORT).show()
        }else{

        }
    }
}