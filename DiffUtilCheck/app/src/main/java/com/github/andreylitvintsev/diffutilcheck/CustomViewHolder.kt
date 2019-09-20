package com.github.andreylitvintsev.diffutilcheck

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView


class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val textView: TextView = view.findViewById(R.id.textView)
    val cardView: MaterialCardView = view.findViewById(R.id.cardView)

}
