package com.example.sebbiatest.ui.fragments

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.sebbiatest.R
import com.example.sebbiatest.app.App
import com.example.sebbiatest.core.util.Resource
import com.example.sebbiatest.databinding.FragmentNewsCategoryBinding
import com.example.sebbiatest.domain.model.NewsCategory
import com.example.sebbiatest.domain.repository.NewsRepository
import com.example.sebbiatest.ui.viewmodels.NewsCategoryViewModel
import com.example.sebbiatest.ui.viewmodels.NewsCategoryViewModelFactory
import javax.inject.Inject

class NewsCategoryFragment : Fragment(), View.OnClickListener {

    @Inject lateinit var newsRepository: NewsRepository

    private lateinit var viewModel: NewsCategoryViewModel

    private var _binding: FragmentNewsCategoryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        (requireContext().applicationContext as App).appComponent.inject(this)
        _binding = FragmentNewsCategoryBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtonListeners()
        viewModel = NewsCategoryViewModelFactory(repository = newsRepository).create(NewsCategoryViewModel::class.java)
        viewModel.newsCategory.observe(viewLifecycleOwner) { newsCategoryResponse ->
            when (newsCategoryResponse) {
                is Resource.Success -> newsCategoryResponse.data?.let {
                    //TODO: hideprogressbar
                    initViews(it)
                }

                is Resource.Loading -> {

                }

                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        newsCategoryResponse.message,
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

    private fun initButtonListeners() {
        binding.newsCategoryBtn1.setOnClickListener(this)
        binding.newsCategoryBtn2.setOnClickListener(this)
        binding.newsCategoryBtn3.setOnClickListener(this)
    }

    private fun initViews(list: List<NewsCategory>) {
        //initializing buttons
        binding.newsCategoryBtn1.text = list[0].name
        binding.newsCategoryBtn2.text = list[1].name
        binding.newsCategoryBtn3.text = list[2].name
    }

    override fun onClick(v: View?) {
        //TODO: implement id passing
        findNavController().navigate(R.id.action_newsCategoryFragment_to_newsAnnotationFragment)
    }
}