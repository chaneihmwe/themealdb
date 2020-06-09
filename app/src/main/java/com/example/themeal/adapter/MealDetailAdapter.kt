package com.example.themeal.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.themeal.R
import com.example.themeal.model.MealX
import com.example.themeal.model.MealXX
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.meal_detail_item.view.*
import kotlinx.android.synthetic.main.meal_item.view.*

class MealDetailAdapter(var mealList: List<MealXX> = ArrayList()): RecyclerView.Adapter<MealDetailAdapter.MealDetailViewHolder>() {

    inner class MealDetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        private lateinit var mealList: MealXX

        fun bindMeal(meal: MealXX) {
            this.mealList = meal
            Picasso.get()
                .load(meal.strMealThumb)
                .placeholder(R.drawable.ic_launcher_background)
                .into(itemView.mealDetailImage)
            itemView.mealDetailTitle.text = meal.strMeal
            itemView.mealDetailDec.text = meal.strInstructions

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealDetailViewHolder {
        val mealViewHolder = LayoutInflater.from(parent.context).inflate(R.layout.meal_detail_item,parent,false)
        return MealDetailViewHolder(mealViewHolder)
    }

    override fun getItemCount(): Int = mealList.size

    override fun onBindViewHolder(holder: MealDetailViewHolder, position: Int) {
        holder.bindMeal(mealList[position])
    }

    fun updateMeal(mealList: List<MealXX>) {
        this.mealList = mealList
        notifyDataSetChanged()
    }

}
