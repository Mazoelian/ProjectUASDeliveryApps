package com.example.projectuas_restaurant.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuas_restaurant.Activity.CartListActivity
import com.example.projectuas_restaurant.Adaptor.CategoryAdapter
import com.example.projectuas_restaurant.Adaptor.PopularAdapter
import com.example.projectuas_restaurant.Domain.CategoryDomain
import com.example.projectuas_restaurant.Domain.FoodDomain
import com.example.projectuas_restaurant.R

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var adapter2: RecyclerView.Adapter<*>
    private lateinit var recyclerViewCategoryList: RecyclerView
    private lateinit var recyclerViewPopularList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewCategory()
        recyclerViewPopular()
        bottomNavigation()
    }

    private fun bottomNavigation() {
        val cartBtn = findViewById<LinearLayout>(R.id.cartBtn)
        val homeBtn = findViewById<LinearLayout>(R.id.homeBtn)
        cartBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    CartListActivity::class.java
                )
            )
        }
        homeBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    MainActivity::class.java
                )
            )
        }
    }

    private fun recyclerViewCategory() {
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategoryList = findViewById(R.id.recyclerView)
        recyclerViewCategoryList.layoutManager = linearLayoutManager

        val category = ArrayList<CategoryDomain>()
        category.add(CategoryDomain("Pizza", "cat_1"))
        category.add(CategoryDomain("Burger", "cat_2"))
        category.add(CategoryDomain("Hotdog", "cat_3"))
        category.add(CategoryDomain("Drink", "cat_4"))
        category.add(CategoryDomain("Donut", "cat_5"))

        adapter = CategoryAdapter(category)
        recyclerViewCategoryList.adapter = adapter
    }

    private fun recyclerViewPopular() {
        val linearLayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewPopularList = findViewById(R.id.recyclerView2)
        recyclerViewPopularList.layoutManager = linearLayoutManager

        val foodList = ArrayList<FoodDomain>()
        foodList.add(FoodDomain("Pepperoni Pizza", "pizza1", "slices pepperoni, mozarella cheese, fresh oregano, ground black pepper, pizza sauce", 9.76))
        foodList.add(FoodDomain("Cheese Burger", "burger", "Beef, Gouda Cheese, Special Sauce, Lettuce, Tomato", 8.79))
        foodList.add(FoodDomain("Vegetable Pizza", "pizza3", "Olive oil, Vegetable oil, Pitted kalamata, Cherry Tomatoes, Fresh Oregano, Basil", 8.5))

        adapter2 = PopularAdapter(foodList)
        recyclerViewPopularList.adapter = adapter2
    }
}
