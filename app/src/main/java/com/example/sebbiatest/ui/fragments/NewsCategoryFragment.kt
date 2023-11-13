package com.example.sebbiatest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.sebbiatest.R
import com.example.sebbiatest.app.App
import com.example.sebbiatest.core.util.Resource
import com.example.sebbiatest.databinding.FragmentNewsCategoryBinding
import com.example.sebbiatest.domain.repository.NewsRepository
import com.example.sebbiatest.ui.viewmodels.NewsViewModel
import com.example.sebbiatest.ui.viewmodels.ViewModelFactory
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsCategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsCategoryFragment : Fragment() {

    @Inject lateinit var newsRepository: NewsRepository

    private lateinit var viewModel: NewsViewModel

    private var _binding: FragmentNewsCategoryBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsCategoryInfo: TextView
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
        viewModel = ViewModelFactory(repository = newsRepository).create(NewsViewModel::class.java)
        newsCategoryInfo = binding.newsCategoryInfoTv
        viewModel.newsCategory.observe(viewLifecycleOwner) { newsCategoryResponse ->
            when (newsCategoryResponse) {
                is Resource.Success -> newsCategoryResponse.data?.let {
                    //TODO: hideprogressbar
                    newsCategoryInfo.text = it.toString()
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
}