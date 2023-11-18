package com.example.sebbiatest.ui.fragments

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.sebbiatest.app.App
import com.example.sebbiatest.core.util.Resource
import com.example.sebbiatest.databinding.FragmentNewsDetailsBinding
import com.example.sebbiatest.domain.repository.NewsRepository
import com.example.sebbiatest.ui.viewmodels.NewsDetailsViewModel
import com.example.sebbiatest.ui.viewmodels.factories.NewsViewModelFactory
import javax.inject.Inject

class NewsDetailsFragment : Fragment() {

    @Inject
    lateinit var newsRepository: NewsRepository

    private lateinit var viewModel: NewsDetailsViewModel

    private val args: NewsDetailsFragmentArgs by navArgs()

    private var _binding: FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        (requireContext().applicationContext as App).appComponent.inject(this)
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initPreloadedViews()
        val newsId = args.newsId
        viewModel = NewsViewModelFactory(id = newsId, newsRepository = newsRepository)
            .create(NewsDetailsViewModel::class.java)
        viewModel.newsDetails.observe(viewLifecycleOwner) { newsDetailsResponse ->
            when (newsDetailsResponse) {
                is Resource.Success -> newsDetailsResponse.data?.let {
                    hideProgressBar()
                    binding.newsDetailsFullDescriptionTV.text = Html.fromHtml(it.fullDescription, Html.FROM_HTML_MODE_LEGACY)
                    binding.newsDetailsFullDescriptionTV.movementMethod = LinkMovementMethod.getInstance()
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Toast.makeText(
                        requireContext(),
                        newsDetailsResponse.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun hideProgressBar() {
        binding.apply {
            newsDetailsPB.visibility = View.GONE
            newsDetailsFullDescriptionTV.visibility = View.VISIBLE
            newsDetailsDateTV.visibility = View.VISIBLE
        }
    }

    private fun showProgressBar() {
        binding.apply {
            newsDetailsPB.visibility = View.VISIBLE
        }
    }

    private fun initPreloadedViews() {
        binding.apply {
            newsDetailsTitleTV.text = args.newsTitle
            newsDetailsShortDescriptionTV.text = args.newsShortDescription
            newsDetailsDateTV.text = args.newsDate
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}