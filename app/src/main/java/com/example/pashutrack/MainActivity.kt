package com.example.pashutrack

import com.example.pashutrack.NotificationAdapter
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp


// MainActivity.kt
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Retrieve the username from SharedPreferences
        val sharedPref = getSharedPreferences("UserDetails", MODE_PRIVATE)
        val username = sharedPref.getString("username", "Guest") // Default to "Guest" if no username is stored

        // Set up the button to go to SignUpActivity
        val goToSignUpButton = findViewById<Button>(R.id.goToSignUpButton)
        goToSignUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // 🔥 Firebase initialization check
        if (FirebaseApp.getApps(this).isNotEmpty()) {
            Log.d("FirebaseCheck", "Firebase is initialized!")
        } else {
            Log.d("FirebaseCheck", "Firebase is NOT initialized.")
        }

        // Set the welcome message dynamically with the username
        val welcomeText: TextView = findViewById(R.id.WelcomeUser)
        val welcomeMessage =
            "<small>Welcome back</small><br><big><font color='#A40000'>$username</font></big>"
        welcomeText.text = Html.fromHtml(welcomeMessage, Html.FROM_HTML_MODE_COMPACT)

        // Setting up the RecyclerView for notifications
        val recyclerView = findViewById<RecyclerView>(R.id.notificationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = false

        val notificationList = listOf(
            NotificationItem("Vaccination Due", "Your cow's vaccine is scheduled tomorrow."),
            NotificationItem("Market Update", "Milk prices increased in your area."),
            NotificationItem("Weather Alert", "Heavy rain expected this week.")
        )

        recyclerView.adapter = NotificationAdapter(notificationList)

        // Set up the toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    // Options menu (Search, Profile, Notifications)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_search -> {
                val intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.action_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.action_notifications -> {
                Toast.makeText(this, "Notifications clicked", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }
}