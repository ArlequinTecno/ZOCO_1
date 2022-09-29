package com.arlequins.zoco_1.ui.menuDrawer.index


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.arlequins.zoco_1.databinding.FragmentIndexBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IndexFragment : Fragment() {

    private lateinit var indexBinding: FragmentIndexBinding
    private lateinit var indexViewModel: IndexViewModel
    private val adapter by lazy {IndexPagerAdapter(requireActivity())}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        indexViewModel = ViewModelProvider(this)[IndexViewModel::class.java]
        indexBinding = FragmentIndexBinding.inflate(inflater, container, false)

        val tabs: TabLayout = indexBinding.indexTabs
        val pager: ViewPager2 = indexBinding.indexPager
        pager.adapter = adapter
        TabLayoutMediator(tabs, pager) { tab, position ->
            when (position) {
                0 -> tab.text = "Todos"
                1 -> tab.text = "Académicos"
                2 -> tab.text = "Alimentos"
                3 -> tab.text = "Servicios"
                4 -> tab.text = "Promociones"
            }
        }.apply {attach()}

        callBack()
        return indexBinding.root
    }
    private fun callBack(){
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            goToMyproducts()
        }
    }

    private fun goToMyproducts() {
        findNavController().navigate(IndexFragmentDirections.actionNavIndexToNavMyProducts())
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.show()
    }
}