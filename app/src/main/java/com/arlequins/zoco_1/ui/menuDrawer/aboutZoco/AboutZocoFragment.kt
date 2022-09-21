package com.arlequins.zoco_1.ui.menuDrawer.aboutZoco

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arlequins.zoco_1.databinding.FragmentAboutZocoBinding

class AboutZocoFragment : Fragment() {

    private var _binding: FragmentAboutZocoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val aboutZocoViewModel = ViewModelProvider(this)[AboutZocoViewModel::class.java]
        _binding = FragmentAboutZocoBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textAboutZoco
        aboutZocoViewModel.text.observe(viewLifecycleOwner){
            textView.text = it
        }
        return binding.root
    }
}