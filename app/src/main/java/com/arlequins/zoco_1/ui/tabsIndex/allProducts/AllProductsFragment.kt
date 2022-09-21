package com.arlequins.zoco_1.ui.tabsIndex.allProducts

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arlequins.zoco_1.databinding.FragmentAllProductsBinding
//import com.arlequins.zoco_1.model.Article


class AllProductsFragment : Fragment() {

    private lateinit var allProductsBinding: FragmentAllProductsBinding
    private lateinit var allProductsViewModel: AllProductsViewModel
    //private lateinit var articleAdapter: AllProductsAdapter
    //private var articleList: ArrayList<Article> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        allProductsBinding = FragmentAllProductsBinding.inflate(inflater, container, false)
        allProductsViewModel = ViewModelProvider(this)[AllProductsViewModel::class.java]
/*
        allProductsViewModel.loadArticles()
        articleAdapter = AllProductsAdapter(articleList, onItemClicked ={onItemClicked(it)} )

        allProductsBinding.allProductsRecycleView.apply{
            layoutManager = LinearLayoutManager(this@AllProductsFragment.requireContext())
            adapter = articleAdapter
            setHasFixedSize(false)
        }
*/
        return allProductsBinding.root
    }

    //private fun onItemClicked(it: Article) {}

}