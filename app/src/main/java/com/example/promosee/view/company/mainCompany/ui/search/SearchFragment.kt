package com.example.promosee.view.company.mainCompany.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.promosee.R
import com.example.promosee.adapter.GridAdapter
import com.example.promosee.adapter.GridSpacingItemDecoration
import com.example.promosee.databinding.FragmentSearchBinding
import com.example.promosee.model.Result
import com.example.promosee.model.local.preference.InfluencerModel
import com.example.promosee.model.remote.reponse.InfluencersItem
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.ui.detailInfluencer.InfluencerDetailActivity
import com.example.promosee.view.login.LoginViewModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private lateinit var searchViewModel: SearchViewModel

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // melakukan setup pada viewmodel
        setupViewModel()

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        searchViewModel.text.observe(viewLifecycleOwner) {}
        return root
    }

    private fun setupViewModel() {
        searchViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity().application)
        )[SearchViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.getInfluencrs().observe(requireActivity()){result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.expired.visibility = View.GONE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.expired.visibility = View.GONE
                    Log.e("test data", result.data.influencers.toString())
                    val allInfluencer: List<InfluencersItem> = result.data.influencers as List<InfluencersItem>
                    addInfluencerData(allInfluencer)
                }
                is Result.Error -> {
                    Log.e("error msg", result.error)
                    if(result.error.trim() == "HTTP 401"){
                        binding.progressBar.visibility = View.GONE
                        binding.expired.visibility = View.VISIBLE
                    }
                }
            }

        }


//        val dummyInfluecers: List<InfluencerModel> = listOf(
//            InfluencerModel(
//                username = "Lee Je Eun",
//                email = "ben@gmail.com",
//                password = "123",
//                categories = catDum,
//            ),
//            InfluencerModel(
//                username = "Lee Je Eun",
//                email = "ben@gmail.com",
//                password = "123",
//                categories = catDum,
//            ),
//            InfluencerModel(
//                username = "Lee Je Eun",
//                email = "ben@gmail.com",
//                password = "123",
//                categories = catDum,
//            ),
//            InfluencerModel(
//                username = "Lee Je Eun",
//                email = "ben@gmail.com",
//                password = "123",
//                categories = catDum,
//            ),
//            InfluencerModel(
//                username = "Lee Je Eun",
//                email = "ben@gmail.com",
//                password = "123",
//                categories = catDum,
//            )
//        )

//        addInfluencerDataold(dummyInfluecers)
    }

    private fun addInfluencerData(allInfluencer: List<InfluencersItem>) {

        // membuat jumlah kolom dalam grid
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewRecom.layoutManager = gridLayoutManager

        // membuat jarak antar item dengan satuan dp
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_5dp)
        val includeEdge = false

        // mengset jarak antar item
        binding.recyclerViewRecom.addItemDecoration(GridSpacingItemDecoration(2, spacingInPixels, includeEdge))

        // memasukkan data ke adapter
        val adapter = GridAdapter(allInfluencer)
        binding.recyclerViewRecom.adapter = adapter

        adapter.setOnItemClickCallback(object : GridAdapter.OnItemClickCallback {
            override fun onItemClicked(influencerData: InfluencersItem) {
//                showSelectedUsers(username)
                Log.e("test item", influencerData.igUsername.toString())
                showSelectedInfluencer(influencerData)
            }
        })

    }

    private fun showSelectedInfluencer(influencerData: InfluencersItem) {
        val moveIntent = Intent(requireContext(), InfluencerDetailActivity::class.java)
        moveIntent.putExtra("username",influencerData.username)
        startActivity(moveIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}