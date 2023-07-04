package com.pedroluis.projects.bookcatalog.app.list.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pedroluis.projects.bookcatalog.R
import com.pedroluis.projects.bookcatalog.app.list.model.BookListModel
import com.pedroluis.projects.bookcatalog.app.list.view.viewholder.BookListItemViewHolder

internal class BookListAdapter(
    private val list: MutableList<BookListModel>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookListItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.book_catalog_list_adapter, parent, false)
        )

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BookListItemViewHolder).bind(list[position])
    }
}
