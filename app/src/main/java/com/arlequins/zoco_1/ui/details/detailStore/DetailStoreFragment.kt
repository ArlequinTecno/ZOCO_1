package com.arlequins.zoco_1.ui.details.detailStore

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.FragmentDetailStoreBinding
import com.arlequins.zoco_1.model.Store
import com.google.android.material.chip.Chip

class DetailStoreFragment : Fragment() {

    private lateinit var dstoreBind: FragmentDetailStoreBinding
    private lateinit var dStoreViewModel: DetailStoreViewModel
    private val args: DetailStoreFragmentArgs by navArgs()
    private var chipValue = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        dstoreBind = FragmentDetailStoreBinding.inflate(inflater, container, false)
        dStoreViewModel = ViewModelProvider(this)[DetailStoreViewModel::class.java]

        val store = args.store
        with(dStoreViewModel){
            editResult.observe(viewLifecycleOwner){ boolResult ->
                if(boolResult == true){
                    showMsg("Edición exitosa")
                    goToMyproducts()
                }
            }
            deleteResult.observe(viewLifecycleOwner){ boolResult ->
                if(boolResult == true){
                    showMsg("Se eliminó su negocio!!")
                    goToMyproducts()
                }
            }
        }

        with(dstoreBind){
            nameDetailStoreInputText.setText(store.name)
            locateDetailStoreInputText.setText(store.location)
            descriptionDetailStoreInputText.setText(store.description)

            editStoreFab.setOnClickListener {
                store.name = nameDetailStoreInputText.text.toString()
                store.location = locateDetailStoreInputText.text.toString()
                store.description = descriptionDetailStoreInputText.text.toString()
                store.state = stateDetailStoreChip.text.toString()

                edit(requireContext(), store)
            }
            trashStoreFab.setOnClickListener {
                deleteStore(requireContext(),store.id)
            }

            chipValue = currentStateChip(store.state.toString())
            chipManager(
                stateDetailStoreChip,
                offColor = R.color.warning_color,
                onColor = R.color.accent,
                onImage = R.drawable.ic_eyes,
                offImage = R.drawable.ic_no_eyes,
                offText = R.string.chip_state_product_no_publish,
                onText = R.string.chip_state_store_visibility
            )
            stateDetailStoreChip.setOnClickListener {
                chipValue = !chipValue
                chipManager(
                    stateDetailStoreChip,
                    offColor = R.color.warning_color,
                    onColor = R.color.accent,
                    onImage = R.drawable.ic_eyes,
                    offImage = R.drawable.ic_no_eyes,
                    offText = R.string.chip_state_product_no_publish,
                    onText = R.string.chip_state_store_visibility
                )
                store.state = stateDetailStoreChip.text.toString()
                Log.i("test", store.state.toString())
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
        return dstoreBind.root
    }

    private fun goToMyproducts() {
        findNavController().navigate(DetailStoreFragmentDirections.actionNavDetailStoreFragmentToNavMyProducts())
    }

    override fun onResume(){
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }
    private fun currentStateChip(state: String): Boolean{
        return state == "Publicar"
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
    private fun deleteStore(context: Context,storeID: String?) = AlertDialog.Builder(context).apply {
        setTitle("Editar Producto")
        setMessage("¿Seguro que quiere guardar los cambios?")
        setPositiveButton("Si") { _, _ ->
            dStoreViewModel.deleteStore(storeID)
        }
        setNegativeButton("No"){_, _ ->
            showMsg("No se efectuaron cambios")
        }

        setCancelable(true)
    }.create().show()

    private fun edit(context: Context, store: Store) = AlertDialog.Builder(context).apply {
        setTitle("Editar Producto")
        setMessage("¿Seguro que quiere guardar los cambios?")
        setPositiveButton("Si") { _, _ ->
            dStoreViewModel.edit(store)
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