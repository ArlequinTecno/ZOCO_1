package com.arlequins.zoco_1.ui.menuDrawer.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.arlequins.zoco_1.databinding.FragmentCategoryBinding


class CategoryFragment : Fragment() {

    private lateinit var categoryBinding: FragmentCategoryBinding
    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        categoryBinding = FragmentCategoryBinding.inflate(inflater, container, false)
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]

        val textView: TextView = categoryBinding.textCategory
        categoryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        with(categoryBinding){
            addCategoryFab.setOnClickListener {
                goToNewCategory()
            }
        }
        return categoryBinding.root
    }
    private fun goToNewCategory(){
        findNavController().navigate(CategoryFragmentDirections.actionNavCategoryToNavNewCategory())
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.show()
    }
}