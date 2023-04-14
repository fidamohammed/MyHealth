package com.example.myhealth.presentation.products

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myhealth.R
import com.example.myhealth.databinding.FragmentProductsBinding


class ProductsFragment : Fragment() {

    private lateinit var binding: FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductsBinding.inflate(layoutInflater)

        binding.tvProduct1.movementMethod = LinkMovementMethod.getInstance()
        binding.tvProduct2.movementMethod = LinkMovementMethod.getInstance()

        return binding.root
    }


}