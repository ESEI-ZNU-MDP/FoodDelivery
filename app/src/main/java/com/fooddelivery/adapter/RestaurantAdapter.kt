package com.fooddelivery.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fooddelivery.R
import com.fooddelivery.entities.Restaurant

class RestaurantAdapter(private var restaurantList: MutableList<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(restaurant: Restaurant)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.img_restaurant)
        private val nameTextView: TextView = itemView.findViewById(R.id.restaurant_name)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(restaurantList[position])
                }
            }
        }

        fun bind(restaurant: Restaurant) {
            imageView.setImageResource(restaurant.imageResId)
            nameTextView.text = restaurant.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rv_category, parent, false)
        return RestaurantViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return restaurantList.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        holder.bind(restaurantList[position])
    }

    fun setData(restaurants: List<Restaurant>) {
        restaurantList.clear()
        restaurantList.addAll(restaurants)
        notifyDataSetChanged()
    }
}