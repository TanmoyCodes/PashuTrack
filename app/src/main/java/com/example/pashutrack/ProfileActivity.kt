package com.example.pashutrack

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvUserId: TextView
    private lateinit var tvBio: TextView
    private lateinit var profileImage: ImageView
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_page)

        // Initialize views based on correct IDs from XML
        tvName = findViewById(R.id.tvName)
        tvUserId = findViewById(R.id.tvUserId)
        tvBio = findViewById(R.id.tvBio)
        profileImage = findViewById(R.id.profileImage)
        btnLogout = findViewById(R.id.btnLogout)

        // Sample static data (replace with Firebase data next)
        tvName.text = "Tanmoy Devnath"
        tvUserId.text = "tanmoy@example.com" // or UID from Firebase
        tvBio.text = "Engineer. Dreamer. Learner. 🚀"
        profileImage.setImageResource(R.drawable.rounded_image)

        btnLogout.setOnClickListener {
            Toast.makeText(this, "Logged out!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
