package com.miran_app.miran_table

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miran_app.miran_table.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val adapter = WindowAdapter()
    private var idWindow: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.apply {
            rcView.layoutManager = LinearLayoutManager(this@MainActivity)
            rcView.adapter = adapter
            addNewButton.setOnClickListener {
                val window = Window(idWindow, 10)
                idWindow += 1
                adapter.addWindow(window)
                adapter.lifeCycleForItem(window, 10000)
            }
            removeFirstButton.setOnClickListener {
                adapter.removeFirstWindow()
            }
        }
    }


}