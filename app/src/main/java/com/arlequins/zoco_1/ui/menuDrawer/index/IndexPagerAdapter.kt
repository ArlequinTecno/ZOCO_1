package com.arlequins.zoco_1.ui.menuDrawer.index

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arlequins.zoco_1.ui.tabsIndex.academics.AcademicsFragment
import com.arlequins.zoco_1.ui.tabsIndex.allProducts.AllProductsFragment
import com.arlequins.zoco_1.ui.tabsIndex.discount.DiscountFragment
import com.arlequins.zoco_1.ui.tabsIndex.foods.FoodsFragment
import com.arlequins.zoco_1.ui.tabsIndex.services.ServicesFragment

class IndexPagerAdapter(fm: FragmentActivity) :
    FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AllProductsFragment()
            1 -> AcademicsFragment()
            2 -> FoodsFragment()
            3 -> ServicesFragment()
            4 -> DiscountFragment()
            else -> AllProductsFragment()
        }
    }

}
