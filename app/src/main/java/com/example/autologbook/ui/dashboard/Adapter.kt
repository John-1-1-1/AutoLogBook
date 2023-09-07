package com.example.autologbook.ui.dashboard

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.autologbook.R
import com.example.autologbook.kernel.types.ItemsViewModel

class Adapter(private val mList: List<ItemsViewModel>) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]
        holder.textViewLarge.text = ItemsViewModel.textViewLarge
        holder.textViewSmall.text = ItemsViewModel.textViewSmall
        holder.textViewPrice.text = ItemsViewModel.textViewPrice
        holder.itemView.setOnClickListener({
                v -> Log.d("TAG", holder.textViewLarge.text.toString())
        })

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val textViewLarge: TextView = itemView.findViewById(R.id.textViewLarge)
        val textViewSmall: TextView = itemView.findViewById(R.id.textViewSmall)
        val textViewPrice: TextView = itemView.findViewById(R.id.textViewPrice)

    }
}