package com.example.projectuas_restaurant.Helper

import android.content.Context
import android.widget.Toast
import com.example.projectuas_restaurant.Domain.FoodDomain
import com.example.projectuas_restaurant.Interface.ChangeNumberItemsListener

class ManagementCart(context: Context) {
    private val context: Context = context
    private val tinyDB: TinyDB<FoodDomain> = TinyDB(context)

    fun insertFood(item: FoodDomain) {
        val listFood = getListCart()
        var existAlready = false
        var existingIndex = 0

        for (i in listFood.indices) {
            if (listFood[i].title == item.title) {
                existAlready = true
                existingIndex = i
                break
            }
        }

        if (existAlready) {
            listFood[existingIndex].numberInCart = item.numberInCart
        } else {
            listFood.add(item)
        }
        tinyDB.putListObject("CartList", listFood)
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show()
    }


    fun getListCart(): ArrayList<FoodDomain> {
        return tinyDB.getListObject("CartList", FoodDomain::class.java)
    }

    fun plusNumberFood(
        listfood: ArrayList<FoodDomain>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        listfood[position].numberInCart = listfood[position].numberInCart + 1
        tinyDB.putListObject("CartList", listfood)  // Ubah kunci menjadi "CartList"
        changeNumberItemsListener.changed()
    }

    fun minusNumberFood(
        listfood: ArrayList<FoodDomain>,
        position: Int,
        changeNumberItemsListener: ChangeNumberItemsListener
    ) {
        if (listfood[position].numberInCart == 1) {
            listfood.removeAt(position)
        } else {
            listfood[position].numberInCart = listfood[position].numberInCart - 1
        }
        tinyDB.putListObject("CartList", listfood)  // Ubah kunci menjadi "CartList"
        changeNumberItemsListener.changed()
    }

    fun getTotalFee(): Double {
        val listFood = getListCart()
        var fee = 0.0

        for (food in listFood) {
            fee += food.fee * food.numberInCart
        }
        return fee
    }
}
