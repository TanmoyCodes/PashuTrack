package com.example.pashutrack

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class ProductDetailFragment : Fragment() {

    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_PRICE = "price"
        private const val ARG_AGE = "age"
        private const val ARG_IMAGE_RES = "imageResId"

        fun newInstance(name: String, price: String, age: String, imageResId: Int): ProductDetailFragment {
            return ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putString(ARG_PRICE, price)
                    putString(ARG_AGE, age)
                    putInt(ARG_IMAGE_RES, imageResId)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString(ARG_NAME) ?: ""
        val price = arguments?.getString(ARG_PRICE) ?: ""
        val age = arguments?.getString(ARG_AGE) ?: ""
        val imageResId = arguments?.getInt(ARG_IMAGE_RES) ?: 0

        view.findViewById<TextView>(R.id.detailName).text = name
        view.findViewById<TextView>(R.id.detailPrice).text = price
        view.findViewById<TextView>(R.id.detailAge).text = age
        view.findViewById<ImageView>(R.id.detailImage).setImageResource(imageResId)

        // Fix: Removed the nested setOnClickListener
        view.findViewById<Button>(R.id.buyB).setOnClickListener {
            val orderSummaryFragment = OrderSummaryFragment.newInstance(name, price, age, imageResId)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, orderSummaryFragment)
                .addToBackStack("product_detail")
                .commit()
        }

        // Setup "more" toggle functionality
        val moreText = view.findViewById<TextView>(R.id.detailMore)
        val extraDetails = view.findViewById<TextView>(R.id.extraDetails)

        moreText.setOnClickListener {
            if (extraDetails.visibility == View.VISIBLE) {
                extraDetails.visibility = View.GONE
                moreText.text = "more"
            } else {
                extraDetails.visibility = View.VISIBLE
                moreText.text = "less"
            }
        }
    }
}