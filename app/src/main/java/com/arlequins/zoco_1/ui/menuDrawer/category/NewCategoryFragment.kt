package com.arlequins.zoco_1.ui.menuDrawer.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.arlequins.zoco_1.databinding.FragmentNewCategoryBinding

class NewCategoryFragment : Fragment() {

    private lateinit var newCategoryBinding: FragmentNewCategoryBinding
    private lateinit var newCategoryViewModel: NewCategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newCategoryBinding = FragmentNewCategoryBinding.inflate(inflater, container, false)
        newCategoryViewModel = ViewModelProvider(this)[NewCategoryViewModel::class.java]

        newCategoryViewModel.showMsg.observe(viewLifecycleOwner){ msg ->
            showMsg(msg)
        }
        newCategoryViewModel.createCategorySuccess.observe(viewLifecycleOwner){
            goToCategory()
        }

        with(newCategoryBinding){
            addCategoryButton.setOnClickListener {
                newCategoryViewModel.validateFields(
                    nameCategoryEditText.text.toString(),
                    descriptionCategoryEditText.text.toString(),
                )
            }
        }


        return newCategoryBinding.root
    }
    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }
    private fun goToCategory(){
        findNavController().navigate(NewCategoryFragmentDirections.actionNavNewCategoryToNavCategory())
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}