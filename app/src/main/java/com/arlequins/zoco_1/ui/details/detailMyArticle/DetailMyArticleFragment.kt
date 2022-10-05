package com.arlequins.zoco_1.ui.details.detailMyArticle

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.FragmentDetailMyArticleBinding
import com.arlequins.zoco_1.interfaces.OnFragmentActionListener
import com.arlequins.zoco_1.model.Article
import com.google.android.material.chip.Chip

class DetailMyArticleFragment : Fragment() {


    private lateinit var dmArticleBinding: FragmentDetailMyArticleBinding
    private lateinit var dmArticleViewModel: DetailMyArticleViewModel
    private val args: DetailMyArticleFragmentArgs by navArgs()

    private val itemsCategory = listOf("Académicos", "Alimentos", "Servicios")
    private val itemsStore = listOf("D´Yuca")

    private var chipValue = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View{
        dmArticleBinding = FragmentDetailMyArticleBinding
            .inflate(inflater, container, false)
        dmArticleViewModel = ViewModelProvider(this)[DetailMyArticleViewModel::class.java]

        val article = args.article

        val adapterCategory = ArrayAdapter(requireContext(), R.layout.category_items, itemsCategory)
        val adapterStore = ArrayAdapter(requireContext(), R.layout.category_items, itemsStore)

        with(dmArticleViewModel){
            showMsg.observe(viewLifecycleOwner){ msg ->
                showMsg(msg)

            }
            editResult.observe(viewLifecycleOwner){ boolResult ->
                if (boolResult==true){
                    goToMyproducts()
                    showMsg("Edición exitosa")

                }
            }
            deleteResult.observe(viewLifecycleOwner){ boolResult ->
                if(boolResult == true){
                    goToMyproducts()
                    showMsg("Se eliminó su producto!!")

                }
            }
        }

        with(dmArticleBinding){
            nameArticleInputText.setText(article.name)
            priceArticleInputText.setText(article.price)
            descriptionProductEditText.setText(article.description)
            categoryAutocompleteTextView.setText(article.category)
            storeAutocompleteTextView.setText(article.store)

            categoryAutocompleteTextView.setAdapter(adapterCategory)
            storeAutocompleteTextView.setAdapter(adapterStore)

            trashFab.setOnClickListener {
                deleteArticle(requireContext(), article.id)
            }
            editFab.setOnClickListener{
                article.name = nameArticleInputText.text.toString()
                article.price = priceArticleInputText.text.toString()
                article.description = descriptionProductEditText.text.toString()
                article.category = categoryAutocompleteTextView.text.toString()
                article.state = storeAutocompleteTextView.text.toString()
                article.state = stateDetailArticleChip.text.toString()

                edit(requireContext(), article)
            }

            chipValue = currentStateChip(article.state.toString())
            chipManager(
                stateDetailArticleChip,
                offColor = R.color.warning_color,
                onColor = R.color.accent,
                onImage = R.drawable.ic_check_mark,
                offImage = R.drawable.ic_x_mark,
                offText = R.string.chip_state_product_no_publish,
                onText = R.string.chip_state_product_publish
            )

            stateDetailArticleChip.setOnClickListener {
                chipValue = !chipValue
                chipManager(
                    stateDetailArticleChip,
                    offColor = R.color.warning_color,
                    onColor = R.color.accent,
                    onImage = R.drawable.ic_check_mark,
                    offImage = R.drawable.ic_x_mark,
                    offText = R.string.chip_state_product_no_publish,
                    onText = R.string.chip_state_product_publish
                )
                article.state = stateDetailArticleChip.text.toString()
                Log.i("test", article.state.toString())
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
        return dmArticleBinding.root
    }

    private fun chipManager(
        chip: Chip,
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
    private fun currentStateChip(state: String): Boolean{
        return state == "Publicar"
    }
    private fun goToMyproducts(){
        findNavController().navigate(DetailMyArticleFragmentDirections.actionNavDetailMyArticleFragmentToNavMyProducts())
    }
    private fun deleteArticle(context: Context,articleID: String?) = AlertDialog.Builder(context).apply {
        setTitle("Editar Producto")
        setMessage("¿Seguro que quiere guardar los cambios?")
        setPositiveButton("Si") { _, _ ->
            dmArticleViewModel.deleteArticle(articleID)
        }
        setNegativeButton("No"){_, _ ->
            showMsg("No se efectuaron cambios")
        }

        setCancelable(true)
    }.create().show()
    private fun edit(context: Context, article: Article) = AlertDialog.Builder(context).apply {
        setTitle("Editar Producto")
        setMessage("¿Seguro que quiere guardar los cambios?")
        setPositiveButton("Si") { _, _ ->
            dmArticleViewModel.edit(article)
        }
        setNegativeButton("No"){_, _ ->
            showMsg("El producto no fue modificado")
        }

        setCancelable(true)
    }.create().show()

    private fun showMsg(msg: String?){
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
    }

}