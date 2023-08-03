package com.miran_app.miran_table

import android.content.ClipData.Item
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miran_app.miran_table.databinding.WindowItemBinding
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

class WindowAdapter: RecyclerView.Adapter<WindowAdapter.WindowHolder>() {
    private val windowsList = ArrayList<Window>()
    private var timer: CountDownTimer? = null

    class WindowHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = WindowItemBinding.bind(item)
        val buttonRemove: Button = item.findViewById(R.id.removeThisItemButton)
        fun bind(window: Window) = with(binding) {
            WindowID.text = window.id.toString()
            seconds.text= window.lifeCycle.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.window_item, parent, false)
        return WindowHolder(view)
    }


    override fun getItemCount(): Int {
        return windowsList.size
    }

    override fun onBindViewHolder(holder: WindowHolder, position: Int) {
        holder.bind(windowsList[position])
        holder.buttonRemove.setOnClickListener {
            removeWindow(windowsList[position])
        }
    }


    fun lifeCycleForItem(window: Window, interval: Long) {
        timer = object : CountDownTimer(interval, 1000) {
            override fun onTick(timeM: Long) {
                window.lifeCycle -= 1
                notifyDataSetChanged()
            }

            override fun onFinish() {
                val position = windowsList.indexOf(window)
                if (position > -1) {
                    removeWindow(window)
                }
            }

        }.start()
    }

    fun addWindow(window: Window) {
        windowsList.add(window)
        notifyDataSetChanged()
    }

    fun removeWindow(window: Window) {
        val position: Int = windowsList.indexOf(window)
        windowsList.removeAt(position)
        notifyItemRemoved(position)
        notifyDataSetChanged()
    }

    fun removeFirstWindow() {
        if (windowsList.size != 0) {
            windowsList.removeFirst()
            notifyItemRemoved(0)
        }
        notifyDataSetChanged()
    }
}