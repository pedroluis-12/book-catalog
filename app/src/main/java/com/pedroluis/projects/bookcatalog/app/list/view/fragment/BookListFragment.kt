package com.pedroluis.projects.bookcatalog.app.list.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.pedroluis.projects.bookcatalog.R
import com.pedroluis.projects.bookcatalog.app.list.viewmodel.BookListViewModel
import com.pedroluis.projects.bookcatalog.app.list.viewmodel.factory.BookListViewModelFactory
import com.pedroluis.projects.bookcatalog.app.list.viewmodel.state.BookListViewModelState

internal class BookListFragment : Fragment() {

    private lateinit var listViewModel: BookListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.book_catalog_list_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupObserver()

        listViewModel.getList()
    }

    private fun setupViewModel() {
        val viewModelFactory = BookListViewModelFactory()
        listViewModel = ViewModelProvider(
            this, viewModelFactory
        )[BookListViewModel::class.java]
    }

    private fun setupObserver() {
        listViewModel.listViewState.observe(viewLifecycleOwner) { state ->
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
                is BookListViewModelState.ShowBookList -> {
                    Toast.makeText(
                        requireContext(),
                        state.bookList.firstOrNull()?.modelTitle, Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}