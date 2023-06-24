package com.pedroluis.projects.bookcatalog.app.catalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pedroluis.projects.bookcatalog.R

class CatalogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.book_catalog_activity)
    }
}