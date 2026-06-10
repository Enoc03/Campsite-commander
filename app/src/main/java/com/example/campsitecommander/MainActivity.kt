package com.example.campsitecommander

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val gearItems = ArrayList<String>()
    private val categories = ArrayList<String>()
    private val quantities = ArrayList<Int>()
    private val notes = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtItem = findViewById<EditText>(R.id.edtItem)
        val edtCategory = findViewById<EditText>(R.id.edtCategory)
        val edtQuantity = findViewById<EditText>(R.id.edtQuantity)
        val edtNotes = findViewById<EditText>(R.id.edtNotes)

        val btnAdd = findViewById<Button>(R.id.btnAddGear)
        val btnView = findViewById<Button>(R.id.btnViewGear)

        val txtTotal = findViewById<TextView>(R.id.txtTotal)

        btnAdd.setOnClickListener {

            val item = edtItem.text.toString()
            val category = edtCategory.text.toString()
            val quantityText = edtQuantity.text.toString()
            val note = edtNotes.text.toString()

            if (item.isEmpty() ||
                category.isEmpty() ||
                quantityText.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    getString(R.string.msg_complete_fields),
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            try {

                val quantity = quantityText.toInt()

                gearItems.add(item)
                categories.add(category)
                quantities.add(quantity)
                notes.add(note)

                txtTotal.text = getString(R.string.total_items_packed, gearItems.size)

                Toast.makeText(
                    this,
                    getString(R.string.msg_gear_added),
                    Toast.LENGTH_SHORT
                ).show()

                edtItem.text.clear()
                edtCategory.text.clear()
                edtQuantity.text.clear()
                edtNotes.text.clear()

            } catch (e: NumberFormatException) {

                Toast.makeText(
                    this,
                    getString(R.string.msg_quantity_number),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnView.setOnClickListener {

            val intent = Intent(this, DetailActivity::class.java)

            intent.putStringArrayListExtra(
                "items",
                gearItems
            )

            intent.putStringArrayListExtra(
                "categories",
                categories
            )

            intent.putIntegerArrayListExtra(
                "quantities",
                quantities
            )

            intent.putStringArrayListExtra(
                "notes",
                notes
            )

            startActivity(intent)
        }
    }
}