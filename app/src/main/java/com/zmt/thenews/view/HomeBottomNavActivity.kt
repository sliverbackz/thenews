package com.zmt.thenews.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.zmt.thenews.R
import com.zmt.thenews.databinding.ActivityBottomNavNewsBinding
import com.zmt.thenews.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeBottomNavActivity : AppCompatActivity() {

    private var _binding: ActivityBottomNavNewsBinding? = null

    private val binding: ActivityBottomNavNewsBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private val appBarConfiguration: AppBarConfiguration by lazy {
        AppBarConfiguration(setOf(R.id.categorical_news_fragment))
    }

    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_main_fragment) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_news, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_item_search) {
            navController.navigate(R.id.news_search_activity)
        }
        return true
//        return item.onNavDestinationSelected(findNavController(R.id.nav_host_main_fragment))
//                || super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityBottomNavNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_top_headlines -> {
                    navController.navigate(R.id.categorical_news_fragment)
                    true
                }
                R.id.menu_saved_news -> {
                    navController.navigate(R.id.saved_news_fragment)
                    true
                }
                else -> {
                    true
                }
            }
        }

        binding.bottomNavigation.setOnNavigationItemReselectedListener { item ->

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    init {
        lifecycleScope.launchWhenResumed {
            viewModel.fetchAllNewSources()
        }
    }
}