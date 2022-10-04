package com.arlequins.zoco_1.ui.menuDrawer.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.arlequins.zoco_1.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var settingsBinding: FragmentSettingsBinding
    private lateinit var settingsViewModel: SettingsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        settingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)
        settingsViewModel = ViewModelProvider(this)[SettingsViewModel::class.java]


        return settingsBinding.root
    }

}