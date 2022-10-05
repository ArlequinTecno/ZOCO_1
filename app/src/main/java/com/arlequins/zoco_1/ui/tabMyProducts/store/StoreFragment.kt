package com.arlequins.zoco_1.ui.tabMyProducts.store

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlequins.zoco_1.databinding.FragmentStoreBinding
import com.arlequins.zoco_1.interfaces.OnFragmentActionListener
import com.arlequins.zoco_1.model.Store
import com.arlequins.zoco_1.ui.tabMyProducts.store.sotreAdapter.StoreAdapter

class StoreFragment : Fragment() {
    private var listenerInterface : OnFragmentActionListener? = null
    private lateinit var storeBinding: FragmentStoreBinding
    private lateinit var storeViewModel: StoreViewModel
    private lateinit var storeAdapter: StoreAdapter
    private val storeList: ArrayList<Store> = ArrayList()
    private lateinit var llmanager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        storeBinding = FragmentStoreBinding.inflate(inflater, container, false)
        storeViewModel = ViewModelProvider(this)[StoreViewModel::class.java]
        llmanager = LinearLayoutManager(this@StoreFragment.requireContext())

        with(storeViewModel){
            loadStore()

            showMsg.observe(viewLifecycleOwner){ msg ->
                showMsg(msg)
            }
            storeListL.observe(viewLifecycleOwner){ storeListLoad ->
                storeAdapter.appendItems(storeListLoad)
            }
        }
        storeAdapter = StoreAdapter(
            storeList,
            onItemClicked = {onStoreClicked(it)}
        )
        with(storeBinding){
            storeRecyclerView.apply {
                layoutManager = llmanager
                adapter = storeAdapter
                setHasFixedSize(false)
            }
        }

        return storeBinding.root
    }

    private fun onStoreClicked(it: Store) {
        listenerInterface?.onClickedStore(it)
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
    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }

}