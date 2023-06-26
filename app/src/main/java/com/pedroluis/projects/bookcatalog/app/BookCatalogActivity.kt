package com.pedroluis.projects.bookcatalog.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.pedroluis.projects.bookcatalog.R
import com.pedroluis.projects.bookcatalog.app.list.BookListLauncher

class BookCatalogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_catalog_activity)

        setupListFragment()
    }

    private fun setupListFragment() {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.book_catalog_list_frame, BookListLauncher.newInstance())
            .commit()
    }
}