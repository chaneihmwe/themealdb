package com.example.themeal.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal.R
import com.example.themeal.model.MealX
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_item.view.*

class MealFilterAdapter(var mealList: List<MealX> = ArrayList()): RecyclerView.Adapter<MealFilterAdapter.MealViewHolder>() {

    var clickListener: ClickListener? = null
    fun setOnClickListener(clickListener: ClickListener){
        this.clickListener = clickListener
    }

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) , View.OnClickListener {
        private lateinit var mealList: MealX

        fun bindMeal(meal: MealX) {
            this.mealList = meal
            Picasso.get()
                .load(meal.strMealThumb)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.mealImage)
            itemView.mealTitle.text = meal.strMeal

        }

        override fun onClick(v: View?) {
            clickListener?.onClick(mealList)
        }

        init {
            itemView.setOnClickListener(this)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val mealViewHolder = LayoutInflater.from(parent.context).inflate(R.layout.meal_item,parent,false)
        return MealViewHolder(mealViewHolder)
    }

    override fun getItemCount(): Int = mealList.size

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bindMeal(mealList[position])
    }

    fun updateMeal(mealList: List<MealX>) {
        this.mealList = mealList
        notifyDataSetChanged()
    }

}

interface ClickListener {
    fun onClick(mealList: MealX)
}