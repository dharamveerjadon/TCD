package com.jordan.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.healthybrains.android.room.DatabaseClient
import com.jordan.R
import com.jordan.common.ui.SpinnerView
import com.jordan.home.adapter.HomeMainAdapter
import com.jordan.home.model.NewsData
import com.jordan.home.viewmodel.CommonViewModel
import com.jordan.room.AppDatabase
import com.jordan.room.model.News
import com.jordan.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommonFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private lateinit var spinnerView: SpinnerView
    private lateinit var appDatabase: AppDatabase
    private var checkDataBase: Boolean = false

    companion object {
        fun newInstance(category: String) = CommonFragment().apply {
            arguments = bundleOf("category" to category)
        }
    }

    private val viewModel: CommonViewModel by lazy { ViewModelProvider(this)[CommonViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.common_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFindId(view)
        observeResponse()
        observeLocalResponse()
        appDatabase = DatabaseClient.getInstance(requireContext()).appDatabase
    }

    override fun onResume() {
        super.onResume()

        if (Utils.isNetworkAvailable(activity)) {
            viewModel.getRows(
                requireActivity(), "in", getString(R.string.API_KEY),
                arguments?.getString("category")!!
            )
        } else {
            viewModel.getLocalStoreNews(requireActivity())
        }

    }

    private fun initFindId(view: View) {
        recyclerView = view.findViewById(R.id.rv_main)
        spinnerView = view.findViewById(R.id.spinner_view)
    }

    private fun observeResponse() {
        viewModel.rowResponse.observe(viewLifecycleOwner, {

            if (it.status.equals("ok")) {

                CoroutineScope(Dispatchers.IO).launch {
                    insertNewsByCategory(
                        viewModel.getStoringNewsOnIndex(arguments?.getString("category")!!),
                        arguments?.getString("category")!!,
                        it.articles
                    )
                }
                recyclerView?.layoutManager = LinearLayoutManager(activity)
                recyclerView?.adapter = HomeMainAdapter(it.articles, requireContext())
                spinnerView.visibility = View.GONE
                checkDataBase = true
            }
        })
        viewModel.rowResponse.removeObservers(this)
    }

    private fun observeLocalResponse() {
        viewModel.newsLocalData.observe(viewLifecycleOwner, {
            for(item in it) {
                if(item.category.equals(arguments?.getString("category")!!)) {
                    recyclerView?.layoutManager = LinearLayoutManager(activity)
                    recyclerView?.adapter = HomeMainAdapter(item.newsList, requireContext())
                }
            }
            spinnerView.visibility = View.GONE
            checkDataBase = true
        })
        viewModel.rowResponse.removeObservers(this)
    }

    private fun insertNewsByCategory(id: Int, category: String, newsList: List<NewsData>) {
        //adding to database
        appDatabase
            .NewsDao()
            .insert(News(id, id, category, newsList))
    }

}