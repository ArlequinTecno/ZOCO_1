package com.arlequins.zoco_1.ui.tabsIndex.services

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlequins.zoco_1.databinding.FragmentAllProductsBinding
import com.arlequins.zoco_1.databinding.FragmentServicesBinding
import com.arlequins.zoco_1.interfaces.OnFragmentActionListener
import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.ui.menuDrawer.index.IndexViewModel
import com.arlequins.zoco_1.ui.tabsIndex.allProducts.ArticleAdapter


class ServicesFragment : Fragment() {
    private var listenerInterface : OnFragmentActionListener? = null
    private lateinit var servicesBinding: FragmentServicesBinding
    private val indexViewModel: IndexViewModel by activityViewModels()
    private lateinit var articleServicesAdapter: ArticleAdapter
    private var articleServicesList: ArrayList<Article> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        servicesBinding = FragmentServicesBinding.inflate(inflater, container, false)

        indexViewModel.loadArticles()
        indexViewModel.showMsg.observe(viewLifecycleOwner){ msg ->
            showMsg(msg)
        }
        indexViewModel.articlesServicesList.observe(viewLifecycleOwner){ articleServicesListLoad ->
            articleServicesAdapter.appendItems(articleServicesListLoad)
        }
        articleServicesAdapter = ArticleAdapter(articleServicesList, onItemClicked ={onArticleClicked(it)} )

        servicesBinding.serviceRecycleView.apply{
            layoutManager = LinearLayoutManager(this@ServicesFragment.requireContext())
            adapter = articleServicesAdapter
            setHasFixedSize(false)

        }
        return servicesBinding.root
    }
    private fun onArticleClicked(it: Article) {
        listenerInterface?.onClickedFragmentCardView(it)
    }
    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
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