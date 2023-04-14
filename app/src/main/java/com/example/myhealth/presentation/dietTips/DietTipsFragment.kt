package com.example.myhealth.presentation.dietTips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myhealth.R
import com.example.myhealth.databinding.FragmentDietTipsBinding
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DietTipsFragment : Fragment() {

    private lateinit var binding: FragmentDietTipsBinding

    private lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var data: MutableList<String>
    private lateinit var flingAdapterView: SwipeFlingAdapterView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDietTipsBinding.inflate(layoutInflater)
        data = mutableListOf(getString(R.string.tip1),getString(R.string.tip2),getString(R.string.tip3),getString(R.string.tip4),getString(R.string.tip5),
            getString(R.string.tip6),getString(R.string.tip7),getString(R.string.tip8),getString(R.string.tip9),getString(R.string.tip10),getString(R.string.tip11))


        flingAdapterView = binding.swipe1
        arrayAdapter = ArrayAdapter(requireContext(), R.layout.card_items, R.id.data, data)
        flingAdapterView.adapter = arrayAdapter

        flingAdapterView.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                data.add(data[0])
                data.removeAt(0)
                arrayAdapter.notifyDataSetChanged()
            }

            override fun onLeftCardExit(o: Any) {

            }

            override fun onRightCardExit(o: Any) {

            }

            override fun onAdapterAboutToEmpty(i: Int) {}
            override fun onScroll(v: Float) {}

        })

        return binding.root
    }

}