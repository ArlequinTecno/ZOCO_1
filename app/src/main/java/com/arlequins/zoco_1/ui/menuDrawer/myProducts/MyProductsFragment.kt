package com.arlequins.zoco_1.ui.menuDrawer.myProducts

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.FragmentMyProductsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MyProductsFragment : Fragment() {
    private var clicked: Boolean = false
    private lateinit var rotateOpen: Animation
    private lateinit var rotateClose: Animation
    private lateinit var fromBottom: Animation
    private lateinit var toBottom: Animation
    private lateinit var fromText: Animation
    private lateinit var toText: Animation

    private lateinit var myProductsBinding: FragmentMyProductsBinding
    private val adapter by lazy {MyProductsPagerAdapter(this)}

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val myProductsViewModel = ViewModelProvider(this)[MyProductsViewModel::class.java]

        myProductsBinding = FragmentMyProductsBinding.inflate(inflater, container, false)


        val tabs: TabLayout = myProductsBinding.myProductsTabs
        val pager: ViewPager2 = myProductsBinding.myProductsPager
        pager.adapter = adapter

        val tabLayoutMediator = TabLayoutMediator(tabs, pager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Mis Artículos"
                }
                1 -> {
                    tab.text = "Mis Negocios"
                }
            }

        }
        tabLayoutMediator.attach()

        val context = this.activity
        rotateOpen = AnimationUtils.loadAnimation(context, R.animator.rotate_open_anim )
        rotateClose = AnimationUtils.loadAnimation(context, R.animator.rotate_close_anim)
        fromBottom = AnimationUtils.loadAnimation(context, R.animator.from_bottom_anim)
        toBottom = AnimationUtils.loadAnimation(context, R.animator.to_bottom_anim)
        fromText = AnimationUtils.loadAnimation(context, R.animator.from_text_anim)
        toText = AnimationUtils.loadAnimation(context, R.animator.to_text_anim)

        with(myProductsBinding){
            productFab.visibility = View.GONE
            storeFab.visibility = View.GONE
            addFab.setOnClickListener {
                onAddFabClicked()
            }
            productFab.setOnClickListener {
                goToNewArticle()
            }
            storeFab.setOnClickListener {
                goToNewStore()
            }
        }

        return myProductsBinding.root
    }
    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar!!.show()

    }
    private fun goToNewStore() {
        findNavController().navigate(MyProductsFragmentDirections.actionNavMyProductsToNavNewStore())
    }
    private fun goToNewArticle() {
        findNavController().navigate(MyProductsFragmentDirections.actionNavMyProductsToNavNewArticle())
    }
    private fun onAddFabClicked() {
        setAnimation(clicked)
        setVisibility(clicked)
        clicked = !clicked
    }
    private fun setVisibility(clicked: Boolean) {
        with(myProductsBinding){
            if(!clicked){
                productFab.visibility = View.VISIBLE
                storeFab.visibility = View.VISIBLE
            }
            else{
                storeFab.visibility = View.GONE
                productFab.visibility = View.GONE
            }
        }
    }
    private fun setAnimation(clicked: Boolean) {
        with(myProductsBinding){
            if(!clicked) {
                productFab.startAnimation(fromBottom)
                storeFab.startAnimation(fromBottom)
                addFab.startAnimation(rotateOpen)
            }
            else{
                productFab.startAnimation(toBottom)
                storeFab.startAnimation(toBottom)
                addFab.startAnimation(rotateClose)
            }
        }
    }
    private fun setClickable(clicked: Boolean){
        with(myProductsBinding){
            if(!clicked){
                productFab.isClickable = true
                storeFab.isClickable = true
            }
            else{
                productFab.isClickable = false
                storeFab.isClickable = false
            }
        }
    }

}

