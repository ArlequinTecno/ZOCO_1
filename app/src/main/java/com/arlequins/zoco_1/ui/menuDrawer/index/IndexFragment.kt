package com.arlequins.zoco_1.ui.menuDrawer.index


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.arlequins.zoco_1.databinding.FragmentIndexBinding
import com.arlequins.zoco_1.interfaces.OnFragmentActionListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class IndexFragment : Fragment() {
    private var listenerInterface : OnFragmentActionListener? = null
    private lateinit var indexBinding: FragmentIndexBinding
    private val adapter by lazy {IndexPagerAdapter(requireActivity())}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        indexBinding = FragmentIndexBinding.inflate(inflater, container, false)
        requireActivity().supportFragmentManager.primaryNavigationFragment

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

        requireActivity().onBackPressedDispatcher.addCallback(this){}
        return indexBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.show()

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