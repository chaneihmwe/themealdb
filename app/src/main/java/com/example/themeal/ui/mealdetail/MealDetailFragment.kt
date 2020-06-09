package com.example.themeal.ui.mealdetail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themeal.R
import com.example.themeal.adapter.MealDetailAdapter
import com.example.themeal.adapter.MealFilterAdapter
import com.example.themeal.ui.meal.MealViewModel
import kotlinx.android.synthetic.main.fragment_meal.*
import kotlinx.android.synthetic.main.fragment_meal.txtLoadError
import kotlinx.android.synthetic.main.fragment_meal_detail.*

class MealDetailFragment : Fragment() {

    private lateinit var mealDetailViewModel: MealDetailViewModel
    private lateinit var mealDetailAdapter: MealDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_meal_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var messageArgs = arguments?.let { MealDetailFragmentArgs.fromBundle(it) }

        var mealID: String? = messageArgs?.mealID
        mealDetailRecyclerView.layoutManager = LinearLayoutManager(context)
        mealDetailAdapter = MealDetailAdapter()
        mealDetailRecyclerView.adapter = mealDetailAdapter
        observeViewModel(mealID!!)
        (activity as AppCompatActivity).supportActionBar?.title = messageArgs?.mealTitle
    }

    fun observeViewModel(mealID : String) {
        var mealDetailViewModel: MealDetailViewModel = ViewModelProvider(this).get(MealDetailViewModel::class.java)
        mealDetailViewModel.loadMealDetail(mealID)
        mealDetailViewModel.getMealDetail().observe(viewLifecycleOwner, Observer {
            mealDetailRecyclerView.visibility = View.VISIBLE
            txtLoadError.visibility = View.GONE
            mealDetailAdapter.updateMeal(it)
        })

        mealDetailViewModel.getLoadingError().observe(viewLifecycleOwner, Observer {
            txtLoadError.visibility = View.VISIBLE
            mealDetailRecyclerView.visibility = View.GONE
        })
    }

}