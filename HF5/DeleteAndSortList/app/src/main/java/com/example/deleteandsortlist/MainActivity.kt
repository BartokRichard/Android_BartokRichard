package com.example.deleteandsortlist

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var mutableItems: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)

        mutableItems = resources.getStringArray(R.array.listitems).toMutableList()

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableItems)
        listView.adapter = adapter

        registerForContextMenu(listView)

        listView.setOnItemLongClickListener { _, view, position, _ ->
            false
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val adapterContextMenuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val position = adapterContextMenuInfo.position
        val view = listView.getChildAt(position)
        val textView = view as TextView

        when (item.itemId) {
            R.id.red -> textView.setTextColor(resources.getColor(android.R.color.holo_red_dark))
            R.id.green -> textView.setTextColor(resources.getColor(android.R.color.holo_green_dark))
            R.id.yellow -> textView.setTextColor(resources.getColor(android.R.color.holo_orange_dark))
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.order -> {
                mutableItems.sort()
                adapter.notifyDataSetChanged()
                return true
            }
            R.id.delete -> {
                mutableItems.clear()
                adapter.notifyDataSetChanged()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


