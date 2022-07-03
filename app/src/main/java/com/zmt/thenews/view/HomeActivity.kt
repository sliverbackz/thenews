package com.zmt.thenews.view

import android.os.Bundle
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
import com.zmt.thenews.databinding.ActivityHomeBinding
import com.zmt.thenews.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private var _binding: ActivityHomeBinding? = null

    private val binding: ActivityHomeBinding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

//    private val appBarConfiguration: AppBarConfiguration by lazy {
//        AppBarConfiguration(setOf(R.id.newsFragment))
//    }
//
//    private val navController: NavController by lazy {
//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navHostFragment.navController
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment))
//                || super.onOptionsItemSelected(item)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
       // setupActionBarWithNavController(navController, appBarConfiguration)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return findNavController(R.id.nav_host_fragment)
//            .navigateUp(appBarConfiguration)
//    }

    init {
        lifecycleScope.launchWhenResumed {
            viewModel.fetchAllNewSources()
        }
    }

}