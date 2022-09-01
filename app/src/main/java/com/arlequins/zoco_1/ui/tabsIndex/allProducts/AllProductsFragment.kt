package com.arlequins.zoco_1.ui.tabsIndex.allProducts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.FragmentAllProductsBinding


class AllProductsFragment : Fragment() {

    private var _binding: FragmentAllProductsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val allProductsViewModel =
            ViewModelProvider(this)[AllProductsViewModel::class.java]

        _binding = FragmentAllProductsBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textAllProducts
        allProductsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return binding.root
    }

}