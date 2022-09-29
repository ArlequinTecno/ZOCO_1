package com.arlequins.zoco_1.ui.tabMyProducts.articles

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arlequins.zoco_1.databinding.FragmentArticlesBinding


class ArticlesFragment : Fragment(){

    private var _binding: FragmentArticlesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val articlesViewModel =
            ViewModelProvider(this)[ArticlesViewModel::class.java]

        _binding = FragmentArticlesBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textArticles
        articlesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


