package com.arlequins.zoco_1.ui.tabsIndex.foods

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
import com.arlequins.zoco_1.databinding.FragmentFoodsBinding
import com.arlequins.zoco_1.interfaces.OnFragmentActionListener
import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.ui.menuDrawer.index.IndexViewModel
import com.arlequins.zoco_1.ui.tabsIndex.allProducts.ArticleAdapter

class FoodsFragment : Fragment() {
    private var listenerInterface : OnFragmentActionListener? = null
    private lateinit var foodsBinding: FragmentFoodsBinding
    private val indexViewModel: IndexViewModel by activityViewModels()
    private lateinit var articleFoodsAdapter: ArticleAdapter
    private var articleFoodsList: ArrayList<Article> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        foodsBinding = FragmentFoodsBinding.inflate(inflater, container, false)


        indexViewModel.loadArticles()
        indexViewModel.showMsg.observe(viewLifecycleOwner){msg ->
            showMsg(msg)
        }
        indexViewModel.articlesFoodsList.observe(viewLifecycleOwner){ foodsList ->
            articleFoodsAdapter.appendItems(foodsList)
        }
        articleFoodsAdapter =  ArticleAdapter(articleFoodsList, onItemClicked ={onArticleClicked(it)} )

        foodsBinding.foodsRecycleView.apply{
            layoutManager = LinearLayoutManager(this@FoodsFragment.requireContext())
            adapter = articleFoodsAdapter
            setHasFixedSize(false)
        }

        return foodsBinding.root
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