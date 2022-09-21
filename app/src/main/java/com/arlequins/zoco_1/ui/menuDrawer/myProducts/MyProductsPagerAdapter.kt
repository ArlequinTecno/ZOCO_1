package com.arlequins.zoco_1.ui.menuDrawer.myProducts



import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arlequins.zoco_1.ui.main.MainActivity
import com.arlequins.zoco_1.ui.tabMyProducts.articles.ArticlesFragment
import com.arlequins.zoco_1.ui.tabMyProducts.store.StoreFragment

/*
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
}*/


class MyProductsPagerAdapter(fm: Fragment) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> {
                ArticlesFragment()
            }
            1 -> {
                StoreFragment()
            }
            else -> {
                ArticlesFragment()
            }
        }
    }
}