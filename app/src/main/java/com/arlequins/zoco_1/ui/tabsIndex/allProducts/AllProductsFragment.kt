package com.arlequins.zoco_1.ui.tabsIndex.allProducts


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlequins.zoco_1.databinding.FragmentAllProductsBinding
import com.arlequins.zoco_1.interfaces.OnFragmentActionListener
import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.ui.menuDrawer.index.IndexViewModel


class AllProductsFragment : Fragment() {
    private var listenerInterface : OnFragmentActionListener? = null
    private lateinit var allProductsBinding: FragmentAllProductsBinding
    private val indexViewModel: IndexViewModel by activityViewModels()
    private lateinit var articleAdapter: ArticleAdapter
    private var articleList: ArrayList<Article> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        allProductsBinding = FragmentAllProductsBinding.inflate(inflater, container, false)

        indexViewModel.loadArticles()

        indexViewModel.showMsg.observe(viewLifecycleOwner){msg ->
            showMsg(msg)
        }
        indexViewModel.articlesList.observe(viewLifecycleOwner){ articleListLoad ->
            articleAdapter.appendItems(articleListLoad)
        }

        articleAdapter = ArticleAdapter(articleList, onItemClicked ={onArticleClicked(it)} )

        allProductsBinding.allProductsRecycleView.apply{
            layoutManager = LinearLayoutManager(this@AllProductsFragment.requireContext())
            adapter = articleAdapter
            setHasFixedSize(false)

        }

        return allProductsBinding.root
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

