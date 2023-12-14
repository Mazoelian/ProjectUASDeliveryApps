package com.example.projectuas_restaurant.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectuas_restaurant.Domain.FoodDomain
import com.example.projectuas_restaurant.Helper.ManagementCart
import com.example.projectuas_restaurant.Interface.ChangeNumberItemsListener
import com.example.projectuas_restaurant.R

class CartListAdapter(
    private val foodDomains: ArrayList<FoodDomain>,
    private val context: Context?,
    private val changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartListAdapter.ViewHolder>() {

    private val managementCart: ManagementCart = ManagementCart(context!!)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate: View =
            LayoutInflater.from(parent.context).inflate(R.layout.viewholder_cart, parent, false)
        return ViewHolder(inflate)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = foodDomains[position].title
        holder.feeEachItem.text = foodDomains[position].fee.toString()
        holder.totalEachItem.text =
            (Math.round(foodDomains[position].numberInCart * foodDomains[position].fee * 100.0) / 100.0).toString()
        holder.num.text = foodDomains[position].numberInCart.toString()

        val drawableResourceId = holder.itemView.context.resources.getIdentifier(
            foodDomains[position].pic,
            "drawable",
            holder.itemView.context.packageName
        )

        Glide.with(holder.itemView.context)
            .load(drawableResourceId)
            .into(holder.pic)

        holder.plusItem.setOnClickListener {
            managementCart.plusNumberFood(
                foodDomains,
                position,
                object : ChangeNumberItemsListener {
                    override fun changed() {
                        notifyDataSetChanged()
                        changeNumberItemsListener.changed()
                    }
                })
        }

        holder.minusItem.setOnClickListener {
            managementCart.minusNumberFood(
                foodDomains,
                position,
                object : ChangeNumberItemsListener {
                    override fun changed() {
                        notifyDataSetChanged()
                        changeNumberItemsListener.changed()
                    }
                })
        }
    }

    override fun getItemCount(): Int {
        return foodDomains.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.titleTxt)
        var feeEachItem: TextView = itemView.findViewById(R.id.feeEachItem)
        var pic: ImageView = itemView.findViewById(R.id.picCart)
        var plusItem: ImageView = itemView.findViewById(R.id.plusCartBtn)
        var minusItem: ImageView = itemView.findViewById(R.id.minusCartBtn)
        var totalEachItem: TextView = itemView.findViewById(R.id.totalEachItem)
        var num: TextView = itemView.findViewById(R.id.numberItemTxt)
    }
}
