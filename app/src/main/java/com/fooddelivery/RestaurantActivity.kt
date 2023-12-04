package com.fooddelivery

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fooddelivery.adapter.MenuAdapter
import com.fooddelivery.entities.MenuItem
import java.io.Serializable

class RestaurantActivity : AppCompatActivity() {

    private lateinit var backButton: Button
    private lateinit var restaurantNameTextView: TextView
    private lateinit var restaurantDescriptionTextView: TextView
    private lateinit var restaurantImageView: ImageView
    private lateinit var menuRecyclerView: RecyclerView
    private lateinit var menuAdapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        backButton = findViewById(R.id.back_button)
        restaurantNameTextView = findViewById(R.id.restaurant_name)
        restaurantDescriptionTextView = findViewById(R.id.restaurant_description)
        restaurantImageView = findViewById(R.id.img_restaurant)
        menuRecyclerView = findViewById(R.id.recycler_view)

        val restaurantName = intent.getStringExtra("restaurant_name")
        val restaurantImageResId = intent.getIntExtra("restaurant_image", 0)
        val restaurantDescription = intent.getStringExtra("restaurant_description")
        val menuList: List<MenuItem> = intent.getSerializableExtra("menuItem") as List<MenuItem>

        println(restaurantName)
        println(restaurantDescription)
        println(menuList.toString())

        showRestaurantDetails(restaurantName, restaurantImageResId, restaurantDescription)

        setupMenuRecyclerView(menuList)

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun showRestaurantDetails(name: String?, imageResId: Int, description: String?) {
        restaurantNameTextView.text = name
        restaurantDescriptionTextView.text = description
        restaurantImageView.setImageResource(imageResId)
    }

    private fun setupMenuRecyclerView(menuList: List<MenuItem>) {
        menuAdapter = MenuAdapter(menuList)
        menuRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@RestaurantActivity, RecyclerView.HORIZONTAL, false)
            adapter = menuAdapter
        }
    }
}
