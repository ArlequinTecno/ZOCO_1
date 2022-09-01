package com.arlequins.zoco_1.ui.tabsIndex.academics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arlequins.zoco_1.databinding.FragmentAcademicsBinding

class AcademicsFragment : Fragment() {

    private var _binding: FragmentAcademicsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val academicsViewModel =
            ViewModelProvider(this)[AcademicsViewModel::class.java]

        _binding = FragmentAcademicsBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textAcademics
        academicsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return binding.root
    }

}