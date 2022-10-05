package com.arlequins.zoco_1.ui.menuDrawer.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.arlequins.zoco_1.databinding.FragmentCategoryBinding
import com.arlequins.zoco_1.model.Category
import com.arlequins.zoco_1.ui.menuDrawer.category.adapter.CategoryAdapter


class CategoryFragment : Fragment() {

    private lateinit var categoryBinding: FragmentCategoryBinding
    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var glmanager: GridLayoutManager
    private val categoryList: ArrayList<Category> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        categoryBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        glmanager = GridLayoutManager(this@CategoryFragment.requireContext(), 2)

        with(categoryViewModel){
            loadCategory()

            showMsg.observe(viewLifecycleOwner){ msg ->
                showMsg(msg)
            }
            categoryResult.observe(viewLifecycleOwner){ categoryListLoad ->
                categoryAdapter.appendItems(categoryListLoad)
            }
        }
        categoryAdapter = CategoryAdapter(
            categoryList,
            onItemClicked={onCategoryClicked(it)}
        )
        with(categoryBinding){
            categoryRecyclerView.apply {
                layoutManager = glmanager
                adapter = categoryAdapter
                setHasFixedSize(false)
            }
            addCategoryFab.setOnClickListener {
                goToNewCategory()
            }
        }

        return categoryBinding.root
    }

    private fun onCategoryClicked(it: Category) {
        showMsg(it.toString())
    }

    private fun goToNewCategory(){
        findNavController().navigate(CategoryFragmentDirections.actionNavCategoryToNavNewCategory())
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.show()
    }
    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }
}