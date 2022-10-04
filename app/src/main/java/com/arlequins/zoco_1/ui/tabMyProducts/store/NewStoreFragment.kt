package com.arlequins.zoco_1.ui.tabMyProducts.store


import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.FragmentNewStoreBinding
import com.google.android.material.chip.Chip

class NewStoreFragment : Fragment(){

    private lateinit var newStoreBinding: FragmentNewStoreBinding
    private lateinit var newStoreViewModel: NewStoreViewModel
    private var chipValue = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newStoreBinding = FragmentNewStoreBinding.inflate(inflater, container, false)
        newStoreViewModel = ViewModelProvider(this)[NewStoreViewModel::class.java]

        newStoreBinding.stateStoreChip.setOnClickListener {
            chipValue = !chipValue
            chipManager(
                newStoreBinding.stateStoreChip,
                offColor = R.color.warning_color,
                onColor = R.color.accent,
                onImage = R.drawable.ic_check_mark,
                offImage = R.drawable.ic_x_mark,
                offText = R.string.chip_state_store_hide,
                onText = R.string.chip_state_store_visibility
            )
        }

        newStoreViewModel.showMsg.observe(viewLifecycleOwner){msg ->
            showMsg(msg)
        }

        newStoreViewModel.createStoreSuccess.observe(viewLifecycleOwner){
            goToMyproducts()
        }

        with(newStoreBinding){
            addStoreButton.setOnClickListener {
                newStoreViewModel.validateFields(
                    nameStoreEditText.text.toString(),
                    descriptionStoreEditText.text.toString(),
                    locationStoreEditText.text.toString(),
                    stateStoreChip.text.toString(),
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
        return newStoreBinding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    private fun goToMyproducts(){
        findNavController().navigate(NewStoreFragmentDirections.actionNavNewStoreToNavMyProducts())
    }
    private fun showMsg(msg: String?) {
        Toast.makeText(requireActivity(), msg, Toast.LENGTH_LONG).show()
    }
    private fun chipManager(chip: Chip,
                            offColor: Int?,
                            onColor: Int?,
                            offImage: Int?,
                            onImage: Int?,
                            offText: Int?,
                            onText : Int?
    ){
        with(chip){
            chipIcon = if (!chipValue) {
                offText?.let { setText(it) }
                offColor?.let { setChipBackgroundColorResource(it) }
                offImage?.let { ContextCompat.getDrawable(this.context, it) }
            } else {
                onText?.let { setText(it) }
                onColor?.let { setChipBackgroundColorResource(it) }
                onImage?.let { ContextCompat.getDrawable(this.context, it) }
            }
        }
    }


}