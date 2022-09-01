package com.arlequins.zoco_1.ui.tabsIndex.foods

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arlequins.zoco_1.databinding.FragmentFoodsBinding

class FoodsFragment : Fragment() {

    private var _binding: FragmentFoodsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val foodsViewModel =
            ViewModelProvider(this)[FoodsViewModel::class.java]

        _binding = FragmentFoodsBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textFoods
        foodsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return binding.root
    }

}