package com.nadarm.yogiyo.ui.activity

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.nadarm.yogiyo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpNavigation()

//
//
//        bottom_navigation.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.item_main_home -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_mainFoodFragment)
//                R.id.item_main_search -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_mainSearchFragment)
//                R.id.item_main_order -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_mainOrderListFragment)
//                R.id.item_main_user -> findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_mainMyInfoFragment)
//            }
//            true
//        }
    }

    private fun setUpNavigation() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(bottom_navigation, navHostFragment.navController)
    }


}
