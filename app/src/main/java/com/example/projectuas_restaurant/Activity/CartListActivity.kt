package com.example.projectuas_restaurant.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projectuas_restaurant.Activity.MainActivity
import com.example.projectuas_restaurant.Adaptor.CartListAdapter
import com.example.projectuas_restaurant.Helper.ManagementCart
import com.example.projectuas_restaurant.Interface.ChangeNumberItemsListener
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.example.projectuas_restaurant.R

class CartListActivity : AppCompatActivity() {
    private var adapter: RecyclerView.Adapter<*>? = null
    private var recyclerViewList: RecyclerView? = null
    private var managementCart: ManagementCart? = null
    private var totalFeeTxt: TextView? = null
    private var taxTxt: TextView? = null
    private var deliveryTxt: TextView? = null
    private var totalTxt: TextView? = null
    private var emptyTxt: TextView? = null
    private var tax = 0.0
    private var scrollView: ScrollView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_list)
        managementCart = ManagementCart(this)
        initView()
        initList()
        calculateCard()
        bottomNavigation()
    }

    private fun bottomNavigation() {
        val cartBtn = findViewById<LinearLayout>(R.id.cartBtn)
        val homeBtn = findViewById<LinearLayout>(R.id.homeBtn)
        cartBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@CartListActivity,
                    CartListActivity::class.java
                )
            )
        }
        homeBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@CartListActivity,
                    MainActivity::class.java
                )
            )
        }
    }

    private fun initList() {
        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewList!!.layoutManager = linearLayoutManager
        adapter =
            CartListAdapter(managementCart?.getListCart() ?: ArrayList(), this, object : ChangeNumberItemsListener {
                override fun changed() {
                    calculateCard()
                }
            })
        recyclerViewList!!.adapter = adapter
        if (managementCart?.getListCart()?.isEmpty() == true) {
            emptyTxt!!.visibility = View.VISIBLE
            scrollView!!.visibility = View.GONE
        } else {
            emptyTxt!!.visibility = View.GONE
            scrollView!!.visibility = View.VISIBLE
        }
    }

    private fun calculateCard() {
        val percentTax = 0.02
        val delivery = 10.0
        val totalFee = managementCart?.getTotalFee() ?: 0.0
        tax = Math.round(totalFee * percentTax * 100.0) / 100.0
        val total = Math.round((totalFee + tax + delivery) * 100.0) / 100.0
        val itemTotal = Math.round(totalFee * 100.0) / 100.0
        totalFeeTxt!!.text = "$$itemTotal"
        taxTxt!!.text = "$$tax"
        deliveryTxt!!.text = "$$delivery"
        totalTxt!!.text = "$$total"
    }

    private fun initView() {
        recyclerViewList = findViewById(R.id.cartView)
        totalFeeTxt = findViewById(R.id.totalFeeTxt)
        taxTxt = findViewById(R.id.taxTxt)
        deliveryTxt = findViewById(R.id.deliveryTxt)
        totalTxt = findViewById(R.id.totalTxt)
        emptyTxt = findViewById(R.id.emptyTxt)
        scrollView = findViewById(R.id.scrollView4)
    }
}
