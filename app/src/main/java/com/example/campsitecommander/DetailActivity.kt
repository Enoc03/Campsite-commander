package com.example.campsitecommander

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val txtDetails = findViewById<TextView>(R.id.txtDetails)

        val items =
            intent.getStringArrayListExtra("items")

        val categories =
            intent.getStringArrayListExtra("categories")

        val quantities =
            intent.getIntegerArrayListExtra("quantities")

        val notes =
            intent.getStringArrayListExtra("notes")

        var display = ""

        if (items != null) {

            for (i in items.indices) {

                display += getString(
                    R.string.detail_format,
                    items[i],
                    categories?.get(i) ?: "",
                    quantities?.get(i) ?: 0,
                    notes?.get(i) ?: ""
                )
            }
        }

        txtDetails.text = display
    }
}