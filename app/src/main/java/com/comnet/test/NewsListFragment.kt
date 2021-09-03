package com.comnet.test

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.comnet.test.databinding.ActivityMainBinding
import com.comnet.test.databinding.FragmentNewsListBinding

class NewsListFragment:Fragment(R.layout.fragment_news_list), NewsAdapter.ItemClick {
    private lateinit var viewModel:NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    private var _binding: FragmentNewsListBinding?=null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNewsListBinding.bind(view)

        showData()
        setupRecyclerView()
    }

    private fun showData() {
        viewModel = ViewModelProvider.AndroidViewModelFactory(activity?.application!!).create(NewsViewModel::class.java)
        viewModel.newsResponse.observe(viewLifecycleOwner, Observer {
            newsAdapter.setNewsData(it.news)
            newsAdapter.notifyDataSetChanged()
        })
    }

    private fun setupRecyclerView() {
        if(!::newsAdapter.isInitialized){
            newsAdapter = NewsAdapter()
        }
        newsAdapter.setItemClick(this)
        binding.recyclerNews.layoutManager= LinearLayoutManager(context)
        binding.recyclerNews.adapter=newsAdapter
    }

    override fun onItemClick(news: News) {
        val action = NewsListFragmentDirections.actionNewsListFragmentToNewsDetailsFragment(news)
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}