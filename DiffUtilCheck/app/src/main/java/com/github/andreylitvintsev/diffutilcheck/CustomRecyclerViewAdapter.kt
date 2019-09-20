package com.github.andreylitvintsev.diffutilcheck

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


class CustomRecyclerViewAdapter : RecyclerView.Adapter<CustomViewHolder>() {

    var strings: List<String> = emptyList()
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun getItemCount(): Int = strings.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.textView.text = strings[position]
        holder.cardView.setOnClickListener {
            Toast
                .makeText(holder.itemView.context, holder.textView.text, Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun update(strings: List<String>) {
        val diffResult = DiffUtil.calculateDiff(CustomDiffUtilCallback(this.strings, strings))
        diffResult.dispatchUpdatesTo(this)
        this.strings = strings
    }

}
