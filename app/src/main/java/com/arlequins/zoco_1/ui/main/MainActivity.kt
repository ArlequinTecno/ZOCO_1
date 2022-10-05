package com.arlequins.zoco_1.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.arlequins.zoco_1.R
import com.arlequins.zoco_1.databinding.ActivityMainBinding
import com.arlequins.zoco_1.interfaces.OnFragmentActionListener
import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.model.Store
import com.arlequins.zoco_1.ui.menuDrawer.index.IndexFragmentDirections
import com.arlequins.zoco_1.ui.menuDrawer.index.IndexFragmentDirections.Companion.actionNavIndexToNavDetailsArticleFragment
import com.arlequins.zoco_1.ui.menuDrawer.myProducts.MyProductsFragmentDirections
import com.arlequins.zoco_1.ui.trolley.TrolleyFragmentDirections
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity :
    AppCompatActivity(),
    OnFragmentActionListener
{
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth
    private lateinit var mainMenu: Menu


    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)




        auth = Firebase.auth
        splashScreen.setKeepOnScreenCondition{false}
        setSupportActionBar(binding.appBarMain.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_index,
                R.id.nav_category,
                R.id.nav_my_products,
                R.id.nav_notifications,
                R.id.nav_about_zoco,
                R.id.nav_settings
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        mainViewModel.showMsg.observe(this){msg ->
            showMsg(msg.toString())
        }
        /*mainViewModel.searchText.observe(this){searchText ->
            val view = findViewById<TextView>(R.id.text_academics)
            view.text = searchText
        }*/

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        mainMenu = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_search -> {
                navController.addOnDestinationChangedListener{_,destination,_->
                    mainViewModel.search(mainMenu, destination)
                }
            }
            R.id.menu_log_out -> {
                onLogout()
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onPrepareOptionsMenu(menu: Menu): Boolean{
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.nav_category -> {
                    invalidateOptionsMenu()
                    menu.findItem(R.id.menu_search).isVisible = false
                }
                R.id.nav_about_zoco -> {
                    invalidateOptionsMenu()
                    menu.findItem(R.id.menu_search).isVisible = false
                }
                R.id.nav_settings -> {
                    invalidateOptionsMenu()
                    menu.findItem(R.id.menu_search).isVisible = false
                }
                R.id.nav_new_store -> {
                    invalidateOptionsMenu()
                    menu.findItem(R.id.menu_search).isVisible = false
                }
                R.id.nav_new_article -> {
                    invalidateOptionsMenu()
                    menu.findItem(R.id.menu_search).isVisible = false
                }
                R.id.nav_details_article_fragment -> {
                    invalidateOptionsMenu()
                    menu.findItem(R.id.menu_search).isVisible = false
                }
                R.id.nav_trolley_fragment -> {
                    invalidateOptionsMenu()
                    menu.findItem(R.id.menu_search).isVisible = false
                }
                else -> {
                    menu.findItem(R.id.menu_search).isVisible = true
                }
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    private fun onLogout() = AlertDialog.Builder(this).apply {
        setTitle("Salir de ZOCO")
        setMessage("¿Seguro que quiere cerrar sesión?")
        setPositiveButton("Si") { _, _ ->

        }
        setNegativeButton("No"){_, _ ->
            showMsg("Gracias")
        }

        setCancelable(true)
    }.create().show()

    override fun onSupportNavigateUp(): Boolean {
        navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun showMsg(msg: String?){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onClickedFragmentCardView(article: Article) {
        navController.navigate(IndexFragmentDirections.actionNavIndexToNavDetailsArticleFragment(article))
    }

    /*override fun onClickedNotificationCardView(notification: NotificationModel) {
        navController.navigate(NotificationsFragmentDirections.actionNavNotificationsToNavDetailNotificationFragment(notification))
    }*/

    override fun navTrolleyToIndex() {
        navController.navigate(TrolleyFragmentDirections.actionNavTrolleyFragmentToNavIndex())
    }

    override fun onClickedMyArticle(it: Article) {
        navController.navigate(MyProductsFragmentDirections.actionNavMyProductsToNavDetailMyArticleFragment(it))
    }

    override fun onClickedStore(it: Store) {
        navController.navigate(MyProductsFragmentDirections.actionNavMyProductsToNavDetailStoreFragment(it))
    }
}