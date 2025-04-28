package com.example.pashutrack

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProductDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product_detail)
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val age = intent.getStringExtra("age")
        val imageResId = intent.getIntExtra("imageResId", 0)


        findViewById<TextView>(R.id.detailName).text = name
        findViewById<TextView>(R.id.detailPrice).text = price
        findViewById<TextView>(R.id.detailAge).text = age
        findViewById<ImageView>(R.id.detailImage).setImageResource(imageResId)

        val buyButton = findViewById<Button>(R.id.buyButton)
        buyButton.setOnClickListener {
            val intent = Intent(this, OrderSummaryActivity::class.java).apply {
                putExtra("name", name)
                putExtra("price", price)
                putExtra("age", age)
                putExtra("imageResId", imageResId)
            }
            startActivity(intent)
        }
    }
}