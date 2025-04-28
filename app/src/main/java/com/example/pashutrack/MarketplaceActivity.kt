
package com.example.pashutrack
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MarketplaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marketplace)
    }

    fun onItemClick(view: View) {
        val tag = view.tag.toString().split("|")
        val name = tag[0]
        val price = tag[1]
        val age = tag[2]
        val imageName = tag[3]

        val imageResId = resources.getIdentifier(imageName, "drawable", packageName)

        val intent = Intent(this, ProductDetailActivity::class.java).apply {
            putExtra("name", name)
            putExtra("price", price)
            putExtra("age", age)
            putExtra("imageResId", imageResId)
        }
        startActivity(intent)
    }
}