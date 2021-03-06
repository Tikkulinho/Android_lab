package com.example.marketplace.myfares.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketplace.R
import com.example.marketplace.myfares.model.MyFareInfo
import com.example.marketplace.timeline.model.Model_Timeline

class myfare_Adapter (
    private var list: ArrayList<MyFareInfo>,
    private val listener: OnItemClickListener,
    private val listener2: OnItemLongClickListener
) :
    RecyclerView.Adapter<myfare_Adapter.DataViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }

    // 1. user defined ViewHolder type - Embedded class!
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        val textView_name: TextView = itemView.findViewById(R.id.textView_productnamesales)
        val textView_price: TextView = itemView.findViewById(R.id.textView_priceitemlayout)
        val textView_seller: TextView = itemView.findViewById(R.id.textView_sellersales)

        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentPosition = this.adapterPosition
            listener.onItemClick(currentPosition)

        }

        override fun onLongClick(p0: View?): Boolean {
            val currentPosition = this.adapterPosition
            listener2.onItemLongClick(currentPosition)
            return true
        }
    }

    // 2. Called only a few times = number of items on screen + a few more ones
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return DataViewHolder(itemView)
    }


    // 3. Called many times, when we scroll the list
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val currentItem = list[position]
        holder.textView_name.text = currentItem.title
        holder.textView_price.text = currentItem.price_per_unit
        holder.textView_seller.text = currentItem.username
    }

    override fun getItemCount() = list.size

    // Update the list
    fun setData(newlist: ArrayList<MyFareInfo>) {
        list = newlist
    }
}