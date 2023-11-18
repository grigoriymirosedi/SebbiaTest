package com.example.sebbiatest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.sebbiatest.R
import com.example.sebbiatest.app.App
import com.example.sebbiatest.core.util.Resource
import com.example.sebbiatest.databinding.FragmentNewsCategoryBinding
import com.example.sebbiatest.domain.model.NewsCategory
import com.example.sebbiatest.domain.repository.NewsRepository
import com.example.sebbiatest.ui.viewmodels.NewsCategoryViewModel
import com.example.sebbiatest.ui.viewmodels.factories.NewsCategoryViewModelFactory
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
        viewModel =
            NewsCategoryViewModelFactory(repository = newsRepository).create(NewsCategoryViewModel::class.java)
        viewModel.newsCategory.observe(viewLifecycleOwner) { newsCategoryResponse ->
            when (newsCategoryResponse) {
                is Resource.Success -> newsCategoryResponse.data?.let {
                    initViews(it)
                }

                is Resource.Loading -> {

                }

                is Resource.Error -> {
                    showErrorMessage()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showErrorMessage(){
        binding.apply {
        }
    }

    private fun initButtonListeners() {
        binding.apply {
            newsCategoryBtn1.setOnClickListener(this@NewsCategoryFragment)
            newsCategoryBtn2.setOnClickListener(this@NewsCategoryFragment)
            newsCategoryBtn3.setOnClickListener(this@NewsCategoryFragment)
        }
    }

    private fun initViews(list: List<NewsCategory>) {
        //initializing buttons
        binding.apply {
            newsCategoryBtn1.text = list[0].name
            newsCategoryBtn2.text = list[1].name
            newsCategoryBtn3.text = list[2].name
        }
    }

    override fun onClick(v: View?) {
        //TODO: implement id passing
        val action = when (v?.id) {
            binding.newsCategoryBtn1.id -> NewsCategoryFragmentDirections.actionNewsCategoryFragmentToNewsAnnotationFragment(
                NEWS_CATEGORY_ID_1
            )

            binding.newsCategoryBtn2.id -> NewsCategoryFragmentDirections.actionNewsCategoryFragmentToNewsAnnotationFragment(
                NEWS_CATEGORY_ID_2
            )

            binding.newsCategoryBtn3.id -> NewsCategoryFragmentDirections.actionNewsCategoryFragmentToNewsAnnotationFragment(
                NEWS_CATEGORY_ID_3
            )
            else -> throw IllegalArgumentException("Undefined behaviour")
        }
        findNavController().navigate(action)
    }

    companion object {
        const val NEWS_CATEGORY_ID_1 = 0
        const val NEWS_CATEGORY_ID_2 = 1
        const val NEWS_CATEGORY_ID_3 = 2
    }
}