package com.arlequins.zoco_1.ui.menuDrawer.index

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arlequins.zoco_1.databinding.FragmentIndexBinding

class IndexFragment : Fragment() {

    private var _binding: FragmentIndexBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val indexViewModel =
            ViewModelProvider(this)[IndexViewModel::class.java]

        _binding = FragmentIndexBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}