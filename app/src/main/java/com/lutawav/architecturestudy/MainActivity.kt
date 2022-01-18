package com.lutawav.architecturestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lutawav.architecturestudy.ui.blog.BlogFragment
import com.lutawav.architecturestudy.ui.image.ImageFragment
import com.lutawav.architecturestudy.ui.movie.MovieFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieFragment = MovieFragment()
        val blogFragment = BlogFragment()
        val imageFragment = ImageFragment()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.movie -> replaceFragment(movieFragment)
                R.id.blog -> replaceFragment(blogFragment)
                R.id.image -> replaceFragment(imageFragment)
            }
            true
        }

        replaceFragment(movieFragment)

    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }
    }
}