package com.example.sebbiatest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sebbiatest.app.App
import com.example.sebbiatest.core.util.Resource
import com.example.sebbiatest.databinding.FragmentNewsAnnotationBinding
import com.example.sebbiatest.domain.model.NewsAnnotation
import com.example.sebbiatest.domain.repository.NewsRepository
import com.example.sebbiatest.ui.adapters.NewsAnnotationAdapter
import com.example.sebbiatest.ui.viewmodels.NewsAnnotationViewModel
import com.example.sebbiatest.ui.viewmodels.factories.NewsViewModelFactory
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class NewsAnnotationFragment : Fragment() {

    @Inject
    lateinit var newsRepository: NewsRepository
    @Inject
    lateinit var newsAnnotationAdapter: NewsAnnotationAdapter

    private lateinit var viewModel: NewsAnnotationViewModel

    private val args: NewsAnnotationFragmentArgs by navArgs()

    private var _binding: FragmentNewsAnnotationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        (requireContext().applicationContext as App).appComponent.inject(this)
        // Inflate the layout for this fragment
        _binding = FragmentNewsAnnotationBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryId = args.categoryId
        viewModel = NewsViewModelFactory(
            categoryId,
            newsRepository
        ).create(NewsAnnotationViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.newsList.collect {
                newsAnnotationAdapter.submitData(it)
            }
        }
        binding.newsAnnotationRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAnnotationAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}