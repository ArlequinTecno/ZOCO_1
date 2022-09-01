package com.arlequins.zoco_1.ui.tabsIndex.discount

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arlequins.zoco_1.databinding.FragmentDiscountBinding

class DiscountFragment : Fragment() {

    private var _binding: FragmentDiscountBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val discountViewModel =
            ViewModelProvider(this)[DiscountViewModel::class.java]

        _binding = FragmentDiscountBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textDiscount
        discountViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return binding.root
    }

}