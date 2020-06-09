package com.example.themeal.ui.meal

import android.icu.lang.UCharacter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.themeal.R
import com.example.themeal.adapter.MealFilterAdapter
import kotlinx.android.synthetic.main.fragment_meal.*

class MealFragment : Fragment() {

    private lateinit var mealViewModel: MealViewModel
    private lateinit var mealFilterAdapter: MealFilterAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mealViewModel =
                ViewModelProviders.of(this).get(MealViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_meal, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealRecyclerView.layoutManager = GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false)
        mealFilterAdapter = MealFilterAdapter()
        mealRecyclerView.adapter = mealFilterAdapter
        observeViewModel()
    }

    fun observeViewModel() {
        var mealViewModel: MealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)
        mealViewModel.loadMeal("Chicken")
        mealViewModel.getMealByCategory().observe(viewLifecycleOwner, Observer {
            mealRecyclerView.visibility = View.VISIBLE
            txtLoadError.visibility = View.GONE
            mealFilterAdapter.updateMeal(it)
        })

        mealViewModel.getLoadingError().observe(viewLifecycleOwner, Observer {
            txtLoadError.visibility = View.VISIBLE
            mealRecyclerView.visibility = View.GONE
        })
    }
}