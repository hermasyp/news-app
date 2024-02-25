package com.catnip.newsapp.presentation.feature.main.pages.newslist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.catnip.newsapp.base.common.ui.ContentState
import com.catnip.newsapp.base.common.ui.toErrorContentState
import com.catnip.newsapp.base.core.proceedWhen
import com.catnip.newsapp.databinding.FragmentNewsListBinding
import com.catnip.newsapp.presentation.feature.main.MainViewModel
import com.catnip.newsapp.presentation.feature.main.pages.newslist.adapter.NewsListAdapter
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by activityViewModel()
    private val adapter: NewsListAdapter by lazy {
        NewsListAdapter {
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(it.url))
            startActivity(i)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupList()
        getSearchListData()
    }


    private fun getSearchListData() {
        mainViewModel.newsResult.observe(viewLifecycleOwner) {
            it.proceedWhen(
                doOnSuccess = {
                    binding.rvSearchResult.isVisible = true
                    binding.contentStateView.setState(ContentState.NORMAL)
                    it.payload?.let { result -> adapter.submitData(result) }
                },
                doOnError = {
                    binding.rvSearchResult.isVisible = false
                    it.exception?.toErrorContentState()
                        ?.let { e -> binding.contentStateView.setState(e) }
                },
                doOnEmpty = {
                    binding.rvSearchResult.isVisible = false
                    binding.contentStateView.setState(ContentState.EMPTY)
                },
                doOnLoading = {
                    binding.rvSearchResult.isVisible = false
                    binding.contentStateView.setState(ContentState.LOADING)
                }
            )
        }
    }

    private fun setupList() {
        binding.srlSearchResult.setOnRefreshListener {
            getSearchListData()
            binding.srlSearchResult.isRefreshing = false
        }
        binding.rvSearchResult.adapter = this.adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}