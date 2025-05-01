package com.example.pashutrack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class PaymentFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.completeOrder).setOnClickListener {
            Toast.makeText(requireContext(), "Order Completed!", Toast.LENGTH_SHORT).show()

            // Navigate back to home or wherever appropriate
            parentFragmentManager.popBackStack("marketplace", 0)
        }
    }
}