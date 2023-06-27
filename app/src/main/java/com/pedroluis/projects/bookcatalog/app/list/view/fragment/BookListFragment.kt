package com.pedroluis.projects.bookcatalog.app.list.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedroluis.projects.bookcatalog.R
import com.pedroluis.projects.bookcatalog.app.list.model.BookListModel
import com.pedroluis.projects.bookcatalog.app.list.view.adapter.BookListAdapter
import com.pedroluis.projects.bookcatalog.app.list.viewmodel.BookListViewModel
import com.pedroluis.projects.bookcatalog.app.list.viewmodel.factory.BookListViewModelFactory
import com.pedroluis.projects.bookcatalog.app.list.viewmodel.state.BookListViewModelState

internal class BookListFragment : Fragment() {

    private lateinit var bookListRecycler: RecyclerView

    private lateinit var bookListViewModel: BookListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.book_catalog_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bookListRecycler = view.findViewById(R.id.book_list_recycler)

        setupViewModel()
        setupObserver()

        bookListViewModel.getList()
    }

    private fun setupViewModel() {
        val viewModelFactory = BookListViewModelFactory()
        bookListViewModel = ViewModelProvider(
            this, viewModelFactory
        )[BookListViewModel::class.java]
    }

    private fun setupObserver() {
        bookListViewModel.listViewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is BookListViewModelState.ShowLoading -> {
                    Toast.makeText(requireContext(), "carregando...", Toast.LENGTH_SHORT).show()
                }
                is BookListViewModelState.HideLoading -> {
                    Toast.makeText(requireContext(), "Pronto!", Toast.LENGTH_SHORT).show()
                }
                is BookListViewModelState.ShowError -> {
                    Toast.makeText(requireContext(), "erro!", Toast.LENGTH_SHORT).show()
                }
                is BookListViewModelState.ShowErrorTryAgainLimit -> {}
                is BookListViewModelState.ShowBookList -> setupBookListRecycler(state.bookList)
            }
        }
    }

    private fun setupBookListRecycler(list: List<BookListModel>) {
        bookListRecycler.apply {
            layoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )
            adapter = BookListAdapter(list.toMutableList())
        }
    }
}
