package com.arlequins.zoco_1.ui.menuDrawer.index

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.ui.tabsIndex.academics.AcademicsFragment
import com.arlequins.zoco_1.ui.tabsIndex.allProducts.AllProductsFragment
import com.arlequins.zoco_1.ui.tabsIndex.discount.DiscountFragment
import com.arlequins.zoco_1.ui.tabsIndex.foods.FoodsFragment
import com.arlequins.zoco_1.ui.tabsIndex.services.ServicesFragment


private val TAB_TITLES = arrayOf(
    R.string.tab_all_products,
    R.string.tab_academics,
    R.string.tab_foods,
    R.string.tab_services,
    R.string.tab_discounts
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> AllProductsFragment()
            1 -> AcademicsFragment()
            2 -> FoodsFragment()
            3 -> ServicesFragment()
            else -> DiscountFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 5
    }
}