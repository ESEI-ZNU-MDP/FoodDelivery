package com.fooddelivery

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fooddelivery.R
import com.fooddelivery.adapter.RestaurantAdapter
import com.fooddelivery.entities.Restaurant

class HomeActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var restaurantAdapter: RestaurantAdapter
    private lateinit var searchView: SearchView
    private lateinit var restaurantList: List<Restaurant>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recycler_view)
        searchView = findViewById(R.id.search_view)

        restaurantList = getStaticRestaurants()
        setupRecyclerView(restaurantList)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { query ->
                    val filteredList = filterRestaurants(query)
                    restaurantAdapter.setData(filteredList)
                }
                return true
            }
        })
    }

    private fun setupRecyclerView(restaurants: List<Restaurant>) {
        restaurantAdapter = RestaurantAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = restaurantAdapter
        }
        restaurantAdapter.setData(restaurants)
    }

    private fun getStaticRestaurants(): List<Restaurant> {
        return listOf(
            Restaurant("Restaurant A", R.drawable.restaurant_image_1),
            Restaurant("Restaurant B", R.drawable.restaurant_image_2),
            Restaurant("Restaurant C", R.drawable.restaurant_image_3),
            Restaurant("Restaurant D", R.drawable.restaurant_image_4),
            Restaurant("Restaurant E", R.drawable.restaurant_image_5)
        )
    }

    private fun filterRestaurants(query: String): List<Restaurant> {
        return restaurantList.filter { restaurant ->
            restaurant.name.contains(query, ignoreCase = true)
        }
    }
}