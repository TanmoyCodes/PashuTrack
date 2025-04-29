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

class SignUpActivity : AppCompatActivity() {

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
            Log.w("SignUpActivity", "Google sign in failed", e)
            Toast.makeText(this, "Google Sign-In failed.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in) // your layout XML file

        mAuth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        // Google Sign-In Button
        findViewById<com.google.android.gms.common.SignInButton>(R.id.google_sign_in_button)
            .setOnClickListener {
                signInWithGoogle()
            }

        // Email and Password Sign Up Button
        findViewById<Button>(R.id.get_started_button)
            .setOnClickListener {
                signUpWithEmail()
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
                    val user = mAuth.currentUser
                    user?.let {
                        saveUserInfo(it)
                        redirectToMainActivity()
                    }
                } else {
                    Toast.makeText(this, "Authentication Failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun saveUserInfo(user: FirebaseUser) {
        val username = user.displayName ?: user.email?.substringBefore("@")
        val email = user.email
        val photoUrl = user.photoUrl?.toString() ?: "No Photo"

        Log.d("SignUpActivity", "Username: $username")
        Log.d("SignUpActivity", "Email: $email")
        Log.d("SignUpActivity", "Photo URL: $photoUrl")

        Toast.makeText(this, "Welcome $username", Toast.LENGTH_SHORT).show()
    }

    private fun redirectToMainActivity() {
        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun signUpWithEmail() {
        val emailEditText = findViewById<EditText>(R.id.email_edit_text_signup)
        val passwordEditText = findViewById<EditText>(R.id.password_edit_text_signup)

        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and Password must not be empty.", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.length < 6) {
            Toast.makeText(this, "Password must be at least 6 characters.", Toast.LENGTH_SHORT).show()
            return
        }

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    user?.let {
                        saveUserInfo(it)
                        redirectToMainActivity()
                    }
                } else {
                    Toast.makeText(this, "Sign Up Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
