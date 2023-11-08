package com.example.moneyextended

import android.app.LauncherActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MyListAdapter(
    context: Context,
    private val images: List<Int>,
    private val currencies: List<String>,
    private val countries: List<String>,
    private val buyNumbers: List<String>,
    private val sellNumbers: List<String>
) : ArrayAdapter<Int>(context, R.layout.costum_list_item, images) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItemView = convertView
        listItemView = LayoutInflater.from(context).inflate(R.layout.costum_list_item, parent, false)


        // Azonos pozícióban lévő adatok elérése a listákból
        val imageResourceId = images[position]
        val currency = currencies[position]
        val country = countries[position]
        val buyNumber = buyNumbers[position]
        val sellNumber = sellNumbers[position]

        val countryTextView = listItemView.findViewById<TextView>(R.id.country)
        val buyNumberTextView = listItemView.findViewById<TextView>(R.id.buyNumber)
        val sellNumberTextView = listItemView.findViewById<TextView>(R.id.sellNumber)
        val imageView = listItemView.findViewById<ImageView>(R.id.imageView)
        val currencyTextView = listItemView.findViewById<TextView>(R.id.currency)

        countryTextView.text = country
        buyNumberTextView.text = buyNumber
        sellNumberTextView.text = sellNumber
        imageView.setImageResource(imageResourceId)
        currencyTextView.text = currency


        return listItemView
    }
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()


        ft.add(R.id.container, BlankFragment())

        ft.commit()
    }
}
