package com.arlequins.zoco_1.ui.tabsIndex.academics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.arlequins.zoco_1.databinding.FragmentAcademicsBinding
import com.arlequins.zoco_1.interfaces.OnFragmentActionListener
import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.ui.menuDrawer.index.IndexViewModel
import com.arlequins.zoco_1.ui.tabsIndex.allProducts.ArticleAdapter

class AcademicsFragment : Fragment() {
    private var listenerInterface : OnFragmentActionListener? = null
    private lateinit var academicsBinding: FragmentAcademicsBinding
    private val indexViewModel: IndexViewModel by activityViewModels()
    private lateinit var academicAdapter: ArticleAdapter
    private var articleAcademicsList: ArrayList<Article> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        academicsBinding = FragmentAcademicsBinding.inflate(inflater, container, false)
        indexViewModel.loadArticles()

        indexViewModel.showMsg.observe(viewLifecycleOwner){msg ->
            showMsg(msg)
        }
        indexViewModel.articlesAcademicsList.observe(viewLifecycleOwner){ articlesAcademicsListLoad ->
            academicAdapter.appendItems(articlesAcademicsListLoad)
            Log.i("academicos", articleAcademicsList.size.toString())
        }
        academicAdapter = ArticleAdapter(articleAcademicsList, onItemClicked ={onArticleClicked(it)})

        academicsBinding.academicRecyclerView.apply{
            layoutManager = LinearLayoutManager(this@AcademicsFragment.requireContext())
            adapter = academicAdapter
            setHasFixedSize(false)
        }
        return academicsBinding.root
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