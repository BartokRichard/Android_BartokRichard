package com.example.activitystatesavetwo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var checkBox: CheckBox
    private var savedText: String? = null
    private var checkBoxState: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        checkBox = findViewById(R.id.checkBox)

        // Ellenőrizzük, hogy a mentett állapot létezik-e
        if (savedInstanceState != null) {
            savedText = savedInstanceState.getString("savedText")
            checkBoxState = savedInstanceState.getBoolean("checkBoxState")
        }

        // Állapot visszaállítása
        editText.setText(savedText)
        checkBox.isChecked = checkBoxState
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // Elmentjük a szöveget és a CheckBox állapotát
        outState.putString("savedText", editText.text.toString())
        outState.putBoolean("checkBoxState", checkBox.isChecked)
    }
}