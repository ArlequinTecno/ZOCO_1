package com.arlequins.zoco_1.ui.tabMyProducts.articles


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.FragmentNewArticleBinding
import com.google.android.material.chip.Chip

class NewArticleFragment : Fragment() {


    private lateinit var newArticleBinding: FragmentNewArticleBinding
    private lateinit var newArticleViewModel: NewArticleViewModel
    private var chipValue = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newArticleViewModel = ViewModelProvider(this)[NewArticleViewModel::class.java]
        newArticleBinding = FragmentNewArticleBinding.inflate(inflater, container, false)

        val itemsCategory = listOf("Académicos", "Alimentos", "Servicios")
        val itemsStore = listOf("D´Yuca")
        val adapterCategory = ArrayAdapter(requireContext(), R.layout.category_items, itemsCategory)
        val adapterStore = ArrayAdapter(requireContext(), R.layout.category_items, itemsStore)
        newArticleBinding.categoryAutocompleteTextView.setAdapter(adapterCategory)
        newArticleBinding.storeAutocompleteTextView.setAdapter(adapterStore)

            newArticleBinding.stateProductChip.setOnClickListener {
                chipValue = !chipValue
                chipManager(
                    newArticleBinding.stateProductChip,
                    offColor = R.color.warning_color,
                    onColor = R.color.accent,
                    onImage = R.drawable.ic_check_mark,
                    offImage = R.drawable.ic_x_mark,
                    offText = R.string.chip_state_product_no_publish,
                    onText = R.string.chip_state_product_publish
                )
            }

        newArticleViewModel.showMsg.observe(viewLifecycleOwner){msg ->
            showMsg(msg)
        }

        newArticleViewModel.createArticleSuccess.observe(viewLifecycleOwner){
            goToMyproducts()
        }


        with(newArticleBinding){
            addProductButton.setOnClickListener {
                newArticleViewModel.validateFields(
                    nameProductEditText.text.toString(),
                    priceProductEditText.text.toString(),
                    descriptionProductEditText.text.toString(),
                    categoryAutocompleteTextView.text.toString(),
                    storeAutocompleteTextView.text.toString(),
                    stateProductChip.text.toString()
                )
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (isEnabled) {
                        isEnabled = false
                        goToMyproducts()
                    }
                }
            }
        )
        return newArticleBinding.root
    }

    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }
    private fun goToMyproducts(){
        findNavController().navigate(NewArticleFragmentDirections.actionNavNewArticleToNavMyProducts())
    }
    private fun chipManager(chip: Chip,
                            offColor: Int?,
                            onColor: Int?,
                            offImage: Int?,
                            onImage: Int?,
                            offText : Int?,
                            onText : Int?
    ){
        with(chip){
            chipIcon = if(!chipValue){
                offText?.let { setText(it) }
                offColor?.let { setChipBackgroundColorResource(it) }
                offImage?.let { ContextCompat.getDrawable(this.context, it) }
            } else{
                onText?.let { setText(it) }
                onColor?.let { setChipBackgroundColorResource(it) }
                onImage?.let { ContextCompat.getDrawable(this.context, it) }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

}