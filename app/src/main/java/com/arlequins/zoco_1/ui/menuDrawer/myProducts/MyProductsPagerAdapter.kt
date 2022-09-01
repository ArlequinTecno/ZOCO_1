package com.arlequins.zoco_1.ui.menuDrawer.myProducts

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.ui.tabMyProducts.articles.ArticlesFragment
import com.arlequins.zoco_1.ui.tabMyProducts.store.StoreFragment



private val TAB_MY_PRODUCTS_TITLES = arrayOf(
    R.string.tab_article,
    R.string.tab_store
)

class MyProductsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> ArticlesFragment()
            else -> StoreFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_MY_PRODUCTS_TITLES[position])
    }

    override fun getCount(): Int {
        return 2
    }
}