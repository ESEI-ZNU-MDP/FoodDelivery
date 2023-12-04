package com.fooddelivery

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fooddelivery.adapter.RestaurantAdapter
import com.fooddelivery.entities.MenuItem
import com.fooddelivery.entities.Restaurant
import java.io.Serializable

class HomeActivity : AppCompatActivity(), RestaurantAdapter.OnItemClickListener {

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

        restaurantAdapter = RestaurantAdapter(restaurantList.toMutableList())
        restaurantAdapter.setOnItemClickListener(this)

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
        restaurantAdapter = RestaurantAdapter(restaurantList.toMutableList())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = restaurantAdapter
        }
        restaurantAdapter.setOnItemClickListener(this)
        restaurantAdapter.setData(restaurants)
    }


    private fun getStaticRestaurants(): MutableList<Restaurant> {
        val menuRestaurantA = listOf(
            MenuItem("Flat Iron", 18.99, R.drawable.dish_1),
//            MenuItem("Chicken Ceaser Salad", 12.49, R.drawable.dish_2),
//            MenuItem("Salmon Salad", 15.99, R.drawable.dish_3)
        )

        val menuRestaurantB = listOf(
            MenuItem("Tokyo Dragon", 11.99, R.drawable.dish_4),
//            MenuItem("Hokkai Roll", 9.49, R.drawable.dish_5),
//            MenuItem("Ramen", 14.99, R.drawable.dish_6)
        )

        val menuRestaurantC = listOf(
            MenuItem("Ramen", 9.99, R.drawable.dish_7),
//            MenuItem("Chicken Udon", 13.49, R.drawable.dish_8),
//            MenuItem("Salmon Bento", 10.99, R.drawable.dish_9)
        )

        val menuRestaurantD = listOf(
            MenuItem("Beef Burger", 8.99, R.drawable.dish_10),
//            MenuItem("Chips", 11.49, R.drawable.dish_11),
//            MenuItem("Grilled Salmon", 12.99, R.drawable.dish_12)
        )

        val menuRestaurantE = listOf(
            MenuItem("Grilled Lobster", 12.99, R.drawable.dish_13),
//            MenuItem("Grilled Salmon", 10.49, R.drawable.dish_14),
//            MenuItem("Salmon Pasta", 9.99, R.drawable.dish_15)
        )

        return mutableListOf(
            Restaurant("Restaurant A", R.drawable.restaurant_image_1, "Description A", menuRestaurantA),
            Restaurant("Restaurant B", R.drawable.restaurant_image_2, "Description B", menuRestaurantB),
            Restaurant("Restaurant C", R.drawable.restaurant_image_3, "Description C", menuRestaurantC),
            Restaurant("Restaurant D", R.drawable.restaurant_image_4, "Description D", menuRestaurantD),
            Restaurant("Restaurant E", R.drawable.restaurant_image_5, "Description E", menuRestaurantE)
        )
    }
    override fun onItemClick(restaurant: Restaurant) {
        val intent = Intent(this, RestaurantActivity::class.java)

        intent.putExtra("restaurant_name", restaurant.name)
        intent.putExtra("restaurant_image", restaurant.imageResId)
        intent.putExtra("restaurant_description", restaurant.description)
        intent.putExtra("menuItem", restaurant.menuItems as Serializable)

        startActivity(intent)
    }

    private fun filterRestaurants(query: String): List<Restaurant> {
        return restaurantList.filter { restaurant ->
            restaurant.name.contains(query, ignoreCase = true)
        }
    }
}