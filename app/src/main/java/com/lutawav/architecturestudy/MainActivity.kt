package com.lutawav.architecturestudy

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.lutawav.architecturestudy.databinding.ActivityMainBinding
import com.lutawav.architecturestudy.ui.blog.BlogFragment
import com.lutawav.architecturestudy.ui.image.ImageFragment
import com.lutawav.architecturestudy.ui.movie.MovieFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        with(binding) {
            viewPager.adapter = PagerAdapter(supportFragmentManager, lifecycle)
            viewPager.registerOnPageChangeCallback(PageChangeCallback())
            bottomNavigation.setOnItemSelectedListener { navigationSelected(it) }
        }
    }

    private fun navigationSelected(item: MenuItem): Boolean {
        val checked = item.setChecked(true)
        when (checked.itemId) {
            R.id.movie -> {
                binding.viewPager.currentItem = 0
                return true
            }
            R.id.image -> {
                binding.viewPager.currentItem = 1
                return true
            }
            R.id.blog -> {
                binding.viewPager.currentItem = 2
                return true
            }
        }
        return false
    }

    private inner class PagerAdapter(
        fm: FragmentManager, lc: Lifecycle
    ) : FragmentStateAdapter(fm, lc) {

        private val fragments = arrayListOf<Fragment>(
            MovieFragment(), ImageFragment(), BlogFragment()
        )

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment =
            fragments[position]
    }

    private inner class PageChangeCallback : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.bottomNavigation.selectedItemId = when (position) {
                0 -> R.id.movie
                1 -> R.id.image
                2 -> R.id.blog
                else -> error("no such position: $position")
            }
        }
    }
}