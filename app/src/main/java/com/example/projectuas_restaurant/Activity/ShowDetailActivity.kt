package com.example.projectuas_restaurant.Activity

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.projectuas_restaurant.Domain.FoodDomain
import com.example.projectuas_restaurant.Helper.ManagementCart
import com.example.projectuas_restaurant.Helper.TinyDB
import com.example.projectuas_restaurant.R

class ShowDetailActivity : AppCompatActivity() {
    private lateinit var addToCartBtn: TextView
    private lateinit var titleTxt: TextView
    private lateinit var feeTxt: TextView
    private lateinit var descriptionTxt: TextView
    private lateinit var numberOrderTxt: TextView
    private lateinit var plusBtn: ImageView
    private lateinit var minusBtn: ImageView
    private lateinit var picFood: ImageView

    private lateinit var foodObject: FoodDomain
    private var numberOrder = 1
    private lateinit var managementCart: ManagementCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)

        val tinyDB = TinyDB<FoodDomain>(applicationContext)
        managementCart = ManagementCart(this)
        initView()
        getBundle()
    }

    private fun getBundle() {
        foodObject = intent.getSerializableExtra("object") as FoodDomain

        val drawableResourceId = resources.getIdentifier(foodObject.pic, "drawable", packageName)
        Glide.with(this)
            .load(drawableResourceId)
            .into(picFood)

        titleTxt.text = foodObject.title
        feeTxt.text = "$" + foodObject.fee.toString()
        descriptionTxt.text = foodObject.description
        numberOrderTxt.text = numberOrder.toString()

        plusBtn.setOnClickListener {
            numberOrder++
            numberOrderTxt.text = numberOrder.toString()
        }

        minusBtn.setOnClickListener {
            if (numberOrder > 1) {
                numberOrder--
            }
            numberOrderTxt.text = numberOrder.toString()
        }

        addToCartBtn.setOnClickListener {
            foodObject.numberInCart = numberOrder
            managementCart.insertFood(foodObject)
        }
    }

    private fun initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn)
        titleTxt = findViewById(R.id.titleTxt)
        feeTxt = findViewById(R.id.priceTxt)
        descriptionTxt = findViewById(R.id.descriptionTxt)
        numberOrderTxt = findViewById(R.id.numberOrderTxt)
        plusBtn = findViewById(R.id.plusBtn)
        minusBtn = findViewById(R.id.minusBtn)
        picFood = findViewById(R.id.picfood)
    }
}
