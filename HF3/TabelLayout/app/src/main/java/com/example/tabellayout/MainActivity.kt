package com.example.tabellayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

data class Product(val code: String, val name: String, val price: Double)

val productList = arrayListOf<Product>()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addProductButton = findViewById<Button>(R.id.addProductButton)
        val showProductsButton = findViewById<Button>(R.id.showProductsButton)
        val cancelButton = findViewById<Button>(R.id.cancelButton)
        val codeEditText = findViewById<EditText>(R.id.code)
        val nameEditText = findViewById<EditText>(R.id.name)
        val priceEditText = findViewById<EditText>(R.id.price)

        addProductButton.setOnClickListener {
            val code = codeEditText.text.toString()
            val name = nameEditText.text.toString()
            val price = priceEditText.text.toString().toDoubleOrNull() ?: 0.0

            val newProduct = Product(code, name, price)
            productList.add(newProduct)

            codeEditText.text.clear()
            nameEditText.text.clear()
            priceEditText.text.clear()
        }

        cancelButton.setOnClickListener {
            codeEditText.text.clear()
            nameEditText.text.clear()
            priceEditText.text.clear()
        }


        showProductsButton.setOnClickListener {
            val productTable = findViewById<TableLayout>(R.id.productTable)

            // Táblázat törlése (kivéve az első sort)
            productTable.removeViews(1, productTable.childCount - 1)

            // Termékek megjelenítése a táblázatban
            for (product in productList) {
                val row = TableRow(this)

                val codeTextView = TextView(this)
                codeTextView.text = product.code

                val nameTextView = TextView(this)
                nameTextView.text = product.name

                val priceTextView = TextView(this)
                priceTextView.text = product.price.toString()

                val params = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)

                codeTextView.layoutParams = params
                nameTextView.layoutParams = params
                priceTextView.layoutParams = params

                row.addView(codeTextView)
                row.addView(nameTextView)
                row.addView(priceTextView)

                productTable.addView(row)
            }
        }
    }
}



