package com.arlequins.zoco_1.ui.tabsIndex.services

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arlequins.zoco_1.databinding.FragmentServicesBinding


class ServicesFragment : Fragment() {

    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val servicesViewModel =
            ViewModelProvider(this)[ServicesViewModel::class.java]

        _binding = FragmentServicesBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textServices
        servicesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return binding.root
    }


}