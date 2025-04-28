package com.example.pashutrack

import com.example.pashutrack.NotificationAdapter
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.TypedValue
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import android.view.View
import android.widget.LinearLayout


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val welcomeText: TextView = findViewById(R.id.WelcomeUser)
        val username = "Tanmoy"




        val recyclerView = findViewById<RecyclerView>(R.id.notificationsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = false

        val notificationList = listOf(
            NotificationItem("Vaccination Due", "Your cow's vaccine is scheduled tomorrow."),
            NotificationItem("Market Update", "Milk prices increased in your area."),
            NotificationItem("Weather Alert", "Heavy rain expected this week."),
            NotificationItem("Vaccination Due", "Your cow's vaccine is scheduled tomorrow."),
            NotificationItem("Market Update", "Milk prices increased in your area.")
        )

        recyclerView.adapter = NotificationAdapter(notificationList)

        val welcomeMessage =
            "<small>Welcome back</small><br><big><font color='#A40000'>$username</font></big>"
        welcomeText.text = Html.fromHtml(welcomeMessage, Html.FROM_HTML_MODE_COMPACT)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.action_search -> {
                    val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
                    linearLayout.removeAllViews()
                    val seachView = layoutInflater.inflate(R.layout.activity_search, null)
                    linearLayout.addView(seachView)

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


