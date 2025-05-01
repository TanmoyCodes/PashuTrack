package com.example.pashutrack

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class OrderSummaryFragment : Fragment() {

    companion object {
        private const val ARG_NAME = "name"
        private const val ARG_PRICE = "price"
        private const val ARG_AGE = "age"
        private const val ARG_IMAGE = "imageResId"

        fun newInstance(name: String, price: String, age: String, imageResId: Int): OrderSummaryFragment {
            return OrderSummaryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NAME, name)
                    putString(ARG_PRICE, price)
                    putString(ARG_AGE, age)
                    putInt(ARG_IMAGE, imageResId)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Retrieve arguments
        val name = arguments?.getString(ARG_NAME) ?: ""
        val price = arguments?.getString(ARG_PRICE) ?: ""
        val age = arguments?.getString(ARG_AGE) ?: ""
        val imageResId = arguments?.getInt(ARG_IMAGE) ?: 0

        // Update UI with product details
        view.findViewById<TextView>(R.id.contactEmail).text = "Purchasing: $name"
        view.findViewById<TextView>(R.id.shippingAddress).text = "Details: $age, $price"

        view.findViewById<Button>(R.id.continueToPayment).setOnClickListener {
            val paymentFragment = ProductDetailFragment.newInstance(name, price, age, imageResId)
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, paymentFragment)
                .addToBackStack("order_summary")
                .commit()
        }
    }
}