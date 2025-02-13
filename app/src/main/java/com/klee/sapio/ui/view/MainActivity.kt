package com.klee.sapio.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.klee.sapio.R
import com.klee.sapio.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val tabLayout = mBinding.tabLayout
        val viewPager = mBinding.viewPager
        viewPager.adapter = FragmentAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(tabLayout, viewPager, true, false) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.feed_title)
                1 -> getString(R.string.search_title)
                2 -> getString(R.string.contribute_title)
                else -> ""
            }
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                val intent = Intent(this@MainActivity, AboutActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
