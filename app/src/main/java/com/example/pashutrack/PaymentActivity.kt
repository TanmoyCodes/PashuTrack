package com.example.pashutrack

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        findViewById<Button>(R.id.completeOrder).setOnClickListener {
            Toast.makeText(this, "Order Completed!", Toast.LENGTH_SHORT).show()
            // Navigate to success screen or home
        }
    }
}
