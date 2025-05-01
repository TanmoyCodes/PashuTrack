package com.example.pashutrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class MarketplaceFragment : Fragment() {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rootView = inflater.inflate(R.layout.fragment_marketplace, container, false)
        setupMarketplace()
        return rootView
    }

    private fun setupMarketplace() {
        // Set click listeners for all cards
        rootView.findViewById<CardView>(R.id.card1).setOnClickListener {
            showProductDetail("White Cow", "₹10000", "₹4000", "Age: 3 years", R.drawable.white_cow)
        }
        rootView.findViewById<CardView>(R.id.card2).setOnClickListener {
            showProductDetail("Brown Horse", "₹12000", "₹4800", "Age: 4 years", R.drawable.brown_horse)
        }
        rootView.findViewById<CardView>(R.id.card3).setOnClickListener {
            showProductDetail("Black Goat", "₹8000", "₹3200", "Age: 2 years", R.drawable.black_goat)
        }
        rootView.findViewById<CardView>(R.id.card4).setOnClickListener {
            showProductDetail("Golden Hen", "₹2000", "₹800", "Age: 1 year", R.drawable.golden_hen)
        }
        rootView.findViewById<CardView>(R.id.card5).setOnClickListener {
            showProductDetail("Spotted Cow", "₹9000", "₹3600", "Age: 2 years", R.drawable.cow)
        }
    }

    private fun showProductDetail(name: String, original: String, discounted: String, age: String, imageRes: Int) {
        // Inflate the product detail view
        val detailView = layoutInflater.inflate(R.layout.fragment_product_detail, null)

        // Set up the detail view
        detailView.findViewById<TextView>(R.id.detailName).text = name
        detailView.findViewById<TextView>(R.id.detailAge).text = age
        detailView.findViewById<ImageView>(R.id.detailImage).setImageResource(imageRes)
        detailView.findViewById<TextView>(R.id.detailPrice).text = discounted

        // Replace the current view with the detail view
        (view as ViewGroup).removeAllViews()
        (view as ViewGroup).addView(detailView)
    }

    fun onBackToMarketplace(view: View) {
        // When back button is clicked, show marketplace again
        (view.parent as ViewGroup).removeAllViews()
        (view.parent as ViewGroup).addView(rootView)
    }
}

