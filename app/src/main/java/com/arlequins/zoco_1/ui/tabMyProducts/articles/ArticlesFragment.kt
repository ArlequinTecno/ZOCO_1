package com.arlequins.zoco_1.ui.tabMyProducts.articles

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlequins.zoco_1.databinding.FragmentArticlesBinding
import com.arlequins.zoco_1.interfaces.OnFragmentActionListener
import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.ui.tabMyProducts.articles.myProductsAdapter.MyArticleAdapter


class ArticlesFragment : Fragment(){
    private var listenerInterface : OnFragmentActionListener? = null
    private lateinit var articleBinding: FragmentArticlesBinding
    private lateinit var articlesViewModel: ArticlesViewModel
    private lateinit var myArticleAdapter: MyArticleAdapter
    private lateinit var llmanager: LinearLayoutManager
    private val myArticleList: ArrayList<Article> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        articleBinding = FragmentArticlesBinding.inflate(inflater, container, false)
        articlesViewModel = ViewModelProvider(this)[ArticlesViewModel::class.java]
        llmanager = LinearLayoutManager(this@ArticlesFragment.requireContext())

        with(articlesViewModel){
            loadMyArticles()

            showMsg.observe(viewLifecycleOwner){ msg ->
                showMsg(msg)
            }
            myArticlesList.observe(viewLifecycleOwner){ articleListLoad ->
                myArticleAdapter.appendItems(articleListLoad)
            }
        }

        myArticleAdapter = MyArticleAdapter(
            myArticleList,
            onItemClicked = {onArticleClicked(it)}
        )
        with(articleBinding){
            myArticlesRecyclerView.apply{
                layoutManager = llmanager
                adapter = myArticleAdapter
                setHasFixedSize(false)
            }
        }

        return articleBinding.root
    }

    private fun onArticleClicked(it: Article) {
        listenerInterface?.onClickedMyArticle(it)
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


