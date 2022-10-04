package com.arlequins.zoco_1.ui.menuDrawer.aboutZoco

import android.content.ContentValues.TAG
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.navigation.fragment.findNavController
import com.arlequins.zoco_1.databinding.FragmentAboutZocoBinding
import com.arlequins.zoco_1.interfaces.OnFragmentActionListener

class AboutZocoFragment : Fragment() {
    private var listenerInterface : OnFragmentActionListener? = null
    private lateinit var aboutZocoBinding: FragmentAboutZocoBinding
    private lateinit var aboutZocoViewModel: AboutZocoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        aboutZocoViewModel = ViewModelProvider(this)[AboutZocoViewModel::class.java]
        aboutZocoBinding = FragmentAboutZocoBinding.inflate(inflater, container, false)


        return aboutZocoBinding.root
    }

    override fun onAttach(context: android.content.Context) {
        super.onAttach(context)
        if (context is OnFragmentActionListener){
            listenerInterface = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerInterface = null
    }

}