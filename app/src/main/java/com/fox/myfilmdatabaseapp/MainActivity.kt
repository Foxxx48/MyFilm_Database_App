package com.fox.myfilmdatabaseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.fox.myfilmdatabaseapp.Tabs.CatalogClothes
import com.fox.myfilmdatabaseapp.Tabs.Panel
import com.fox.myfilmdatabaseapp.Tabs.categories.CatalogCategories
import com.fox.myfilmdatabaseapp.Tabs.products.CatalogProducts

import com.fox.myfilmdatabaseapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        supportFragmentManager.beginTransaction().replace(R.id.content, Panel()).commit()

        binding?.bottomNav?.setOnNavigationItemSelectedListener(this)
        binding?.bottomNav?.selectedItemId = R.id.panelItemBottomNav

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.panelItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, Panel()).commit()
            R.id.catalogProductsItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, CatalogProducts()).commit()
            R.id.catalogClothesItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, CatalogClothes()).commit()
            R.id.catalogCategoriesItemBottomNav -> supportFragmentManager.beginTransaction().replace(R.id.content, CatalogCategories()).commit()
        }

        return true
    }
}