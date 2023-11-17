package com.example.sebbiatest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.example.sebbiatest.R
import com.example.sebbiatest.app.App
import com.example.sebbiatest.core.util.Resource
import com.example.sebbiatest.databinding.FragmentNewsAnnotationBinding
import com.example.sebbiatest.domain.repository.NewsRepository
import com.example.sebbiatest.ui.viewmodels.NewsAnnotationViewModel
import com.example.sebbiatest.ui.viewmodels.NewsCategoryViewModel
import com.example.sebbiatest.ui.viewmodels.NewsViewModelFactory
import javax.inject.Inject

class NewsAnnotationFragment : Fragment() {

    @Inject
    lateinit var newsRepository: NewsRepository

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
        viewModel.newsAnnotation.observe(viewLifecycleOwner) { newsAnnotationResponse ->
            when (newsAnnotationResponse) {
                is Resource.Success -> {
                    binding.newsAnnotationTV.text = newsAnnotationResponse.data.toString()
                }

                is Resource.Loading -> {

                }

                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        newsAnnotationResponse.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}