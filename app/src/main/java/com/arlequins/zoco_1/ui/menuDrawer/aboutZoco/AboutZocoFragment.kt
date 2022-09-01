package com.arlequins.zoco_1.ui.menuDrawer.aboutZoco

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arlequins.zoco_1.R

class AboutZocoFragment : Fragment() {

    private lateinit var viewModel: AboutZocoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_zoco, container, false)
    }
}