package com.arlequins.zoco_1.ui.details.detailsStore

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arlequins.zoco_1.R

class DetailStoreFragment : Fragment() {

    companion object {
        fun newInstance() = DetailStoreFragment()
    }

    private lateinit var viewModel: DetailStoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_store, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailStoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

}