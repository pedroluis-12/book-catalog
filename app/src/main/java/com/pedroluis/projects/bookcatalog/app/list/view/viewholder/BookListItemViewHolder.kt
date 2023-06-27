package com.pedroluis.projects.bookcatalog.app.list.view.viewholder

import android.annotation.SuppressLint
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pedroluis.projects.bookcatalog.R
import com.pedroluis.projects.bookcatalog.app.list.model.BookListModel
import com.pedroluis.projects.bookcatalog.commons.extensions.setViewAsButtonForAccessibility
import com.pedroluis.projects.bookcatalog.commons.extensions.setupListToString
import java.security.AccessController.getContext


internal class BookListItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val itemContainer: LinearLayout =
        itemView.findViewById(R.id.list_item_container)
    private val itemAuthorName: AppCompatTextView =
        itemView.findViewById(R.id.list_item_authors_name_text)
    private val itemBookName: AppCompatTextView =
        itemView.findViewById(R.id.list_item_book_name_text)
    private val itemSubjects: AppCompatTextView =
        itemView.findViewById(R.id.list_item_book_subjects_text)
    private val itemDivider: View =
        itemView.findViewById(R.id.list_item_divider)

    @SuppressLint("ResourceType")
    fun bind(item: BookListModel) {
        setupAuthors(item.modelAuthors)
        itemBookName.text = item.modelTitle
        setupSubjects(item.modelSubjects)
        setupDividerVisibility(item.modelLastItem)
        setupClick(item)
    }

    private fun setupAuthors(authors: List<String>) {
        if (authors.isNotEmpty()) {
            itemAuthorName.apply {
                visibility = View.VISIBLE
                text = authors.setupListToString(separator = "-")
            }
        } else {
            itemAuthorName.visibility = View.GONE
        }
    }

    private fun setupSubjects(subjects: List<String>) {
        if (subjects.isNotEmpty()) {
            itemSubjects.apply {
                visibility = View.VISIBLE
                text = subjects.setupListToString("#", ", ")
            }
        } else {
            itemSubjects.visibility = View.GONE
        }
    }

    private fun setupDividerVisibility(isLastItem: Boolean) {
        if (isLastItem) {
            itemDivider.visibility = View.GONE
        } else {
            itemDivider.visibility = View.VISIBLE
        }
    }

    private fun setupClick(item: BookListModel) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val outValue = TypedValue()
            itemView.context.theme.resolveAttribute(
                android.R.attr.selectableItemBackgroundBorderless, outValue, true
            )
            itemContainer.foreground = ContextCompat.getDrawable(
                itemView.context, outValue.resourceId
            )
        } else {
            itemContainer.background = ContextCompat.getDrawable(
                itemView.context, R.drawable.book_catalog_custom_ripple_effect
            )
        }
        itemContainer.apply {
            setOnClickListener {
                Toast.makeText(itemView.context, item.modelTitle, Toast.LENGTH_SHORT).show()
            }
            setViewAsButtonForAccessibility()
        }
    }
}
