package com.example.pashutrack

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // 🟢 Retrieve the username from SharedPreferences
        val user = FirebaseAuth.getInstance().currentUser
        val fullName = user?.displayName ?: user?.email?.substringBefore("@") ?: "Guest"
        val firstName = fullName.split(" ")[0].replaceFirstChar { it.uppercase() }

        val welcomeText = findViewById<TextView>(R.id.WelcomeUser)
        val welcomeMessage = """
    <small>Welcome back</small><br>
    <big><font color='#A40000'>$firstName</font></big>
""".trimIndent()
        welcomeText.text = Html.fromHtml(welcomeMessage, Html.FROM_HTML_MODE_COMPACT)



        // 🔘 RecyclerView for notifications
        val recyclerView = findViewById<RecyclerView>(R.id.notificationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = false

        val notificationList = listOf(
            NotificationItem("Vaccination Due", "Your cow's vaccine is scheduled tomorrow."),
            NotificationItem("Market Update", "Milk prices increased in your area."),
            NotificationItem("Weather Alert", "Heavy rain expected this week.")
        )
        recyclerView.adapter = NotificationAdapter(notificationList)
        // 🔘 Firebase Initialization Check
        if (FirebaseApp.getApps(this).isNotEmpty()) {
            Log.d("FirebaseCheck", "Firebase is initialized!")
        } else {
            Log.d("FirebaseCheck", "Firebase is NOT initialized.")
        }

        // 🔘 Button to go back to SignUpActivity (optional)
        val goToSignUpButton = findViewById<Button>(R.id.goToNotificationPage)
        goToSignUpButton.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        // 🔘 Setup Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    // 🛠 Menu Options
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_search -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
            R.id.action_profile -> {
                Toast.makeText(this, "Profile clicked", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            R.id.action_notifications -> {
                Toast.makeText(this, "Notifications clicked", Toast.LENGTH_SHORT).show()

            }
        }
        return true
    }
}
