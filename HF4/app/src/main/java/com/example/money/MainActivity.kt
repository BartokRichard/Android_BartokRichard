    package com.example.money

    import android.app.LauncherActivity
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle

    import android.content.Context
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.ArrayAdapter
    import android.widget.ImageView
    import android.widget.ListView
    import android.widget.TextView

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

            val listView = findViewById<ListView>(R.id.listView)

            // Képek és az egyéb adatok listái
            val images = listOf(
                R.drawable.europeanunion,
                R.drawable.unitedstates,
                R.drawable.unitedkingdom,
                R.drawable.australia,
                R.drawable.canada,
                R.drawable.switzerland,
                R.drawable.denmark,
                R.drawable.hungary
            )

            val currencies = listOf("EUR", "USD", "GBP", "AUD", "CAD", "CHF", "DKK", "HUF")
            val countries = listOf("Euro", "Dolar american", "Lira sterilina", "Dolar austrian ", "dolar canadian", "Franc elvetian", "Corona daneza", "Forint maghiar  ")
            val buyNumbers = listOf("4.4100 RON", "3.9750 RON", "6.1250 RON", "2.9600 RON", "3.0950 RON", "4.2300 RON", "0.5850 RON", "0.0136 RON")
            val sellNumbers = listOf("4.5500 RON", "4.1450 RON", "6.3550 RON", "3.0600 RON", "3.2650 RON", "4.3300 RON", "0.6150 RON", "0.0146 RON")

            // Adapter létrehozása
            val adapter = MyListAdapter(this, images, currencies, countries, buyNumbers, sellNumbers)

            // ListView beállítása az adapterrel
            listView.adapter = adapter
        }
    }
