package com.example.pashutrack

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    private val googleSignInLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val data = result.data
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            val account = task.getResult(ApiException::class.java)
            account?.idToken?.let { firebaseAuthWithGoogle(it) }
        } catch (e: ApiException) {
            Log.w("LoginActivity", "Google sign in failed", e)
            Toast.makeText(this, "Google Sign-In failed.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.log_in) // <-- your login screen layout

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // Google Sign-In Button
        findViewById<com.google.android.gms.common.SignInButton>(R.id.google_login_button)
            .setOnClickListener {
                signInWithGoogle()
            }

        // Email and Password Login Button
        findViewById<Button>(R.id.google_login_button)
            .setOnClickListener {
                loginWithEmail()
            }
    }

    // Check if the user is already logged in when the activity starts
    override fun onStart() {
        super.onStart()

        // Check if the user is already signed in
        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            // If user is already logged in, redirect them to the MainActivity
            redirectToMainActivity()
        }
    }

    private fun signInWithGoogle() {
        val signInIntent = mGoogleSignInClient.signInIntent
        googleSignInLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val isNewUser = task.result?.additionalUserInfo?.isNewUser
                    if (isNewUser == true) {
                        Toast.makeText(this, "No account found with this Google ID.", Toast.LENGTH_SHORT).show()
                        mAuth.signOut() // Sign out immediately if new user detected
                    } else {
                        val user = mAuth.currentUser
                        user?.let {
                            Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                            redirectToMainActivity()
                        }
                    }
                } else {
                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun loginWithEmail() {
        val emailEditText = findViewById<EditText>(R.id.email_edit_text_signup)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text_signup)

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and Password must not be empty.", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    user?.let {
                        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                        redirectToMainActivity()
                    }
                } else {
                    Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun redirectToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish() // Close the LoginActivity so that user can't go back to it
    }
}
