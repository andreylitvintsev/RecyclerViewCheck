package com.github.andreylitvintsev.diffutilcheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*


class MainActivity : AppCompatActivity() {

    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewAdapter = CustomRecyclerViewAdapter()
        
        findViewById<RecyclerView>(R.id.recyclerView).apply {
            adapter = recyclerViewAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        findViewById<Button>(R.id.updateButton).setOnClickListener {
            if (random.nextBoolean()) {
                recyclerViewAdapter.update(generateRandomList(random.nextInt(9) + 1))
            } else {
                recyclerViewAdapter.strings
                    .takeIf { it.isNotEmpty() }
                    ?.let { recyclerViewAdapter.update(modifyList(it)) }
            }
        }
    }

    private fun generateRandomList(size: Int): List<String> {
        return List(size) {
            UUID.randomUUID().toString()
        }
    }

    private fun modifyList(list: List<String>): List<String> {
        return list.toMutableList().apply {
            add(random.nextInt(list.size), UUID.randomUUID().toString())
        }
    }

}
