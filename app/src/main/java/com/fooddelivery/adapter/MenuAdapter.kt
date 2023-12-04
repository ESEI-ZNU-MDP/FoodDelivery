package com.fooddelivery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fooddelivery.R
import com.fooddelivery.entities.MenuItem
import com.fooddelivery.entities.Restaurant

class MenuAdapter(private val menuList: List<MenuItem>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    inner class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dishImageView: ImageView = itemView.findViewById(R.id.img_dish)
        private val dishNameTextView: TextView = itemView.findViewById(R.id.dish_name)
        private val dishPriceTextView: TextView = itemView.findViewById(R.id.dish_price)

        fun bind(menuItem: MenuItem) {
            dishImageView.setImageResource(menuItem.imageResId)
            dishNameTextView.text = menuItem.name
            dishPriceTextView.text = "$${menuItem.price}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_menu, parent, false)
        return MenuViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.bind(menuList[position])
    }

//    fun setData(restaurants: List<Restaurant>) {
//        menuList.clear()
//        menuList.addAll(menuList)
//        notifyDataSetChanged()
//    }
}
