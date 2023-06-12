package com.example.promosee.view.company.mainCompany.ui.search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.promosee.R
import com.example.promosee.adapter.GridAdapter
import com.example.promosee.adapter.GridSpacingItemDecoration
import com.example.promosee.databinding.FragmentSearchBinding
import com.example.promosee.model.Result
import com.example.promosee.model.remote.reponse.InfluencersItem
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.ui.detailInfluencer.InfluencerDetailActivity

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
//        searchViewModel.text.observe(viewLifecycleOwner) {}

        binding.refreshBtn.setOnClickListener{
            getAllInfluencer()
        }
        return root
    }

    // untuk searching -- API blm jadi --
    private fun searchQueue() {
        val searchInfluencer = binding.searchInflu
        searchInfluencer.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.recyclerViewRecom.adapter = null
                    if(query.isEmpty()){
                        getAllInfluencer()
                    }else{
                        searchViewModel.setUsername(query)
                        searchViewModel.getInfluencersSearch().observe(requireActivity()){result ->
                            when(result){
                                is Result.Loading -> {
                                    binding.progressBar.visibility = View.VISIBLE
                                    binding.warningCont.visibility = View.GONE
                                }
                                is Result.Success -> {
                                    binding.progressBar.visibility = View.GONE
                                    binding.warningCont.visibility = View.GONE
                                    Log.e("test data", result.data.influencers.toString())
                                    val allInfluencer: InfluencersItem = InfluencersItem(
                                        username = result.data.influencers?.username,
                                        igFollowers = result.data.influencers?.igFollowers,
                                        ttFollowers = result.data.influencers?.ttFollowers,
                                        ytFollowers = result.data.influencers?.ytFollowers,
                                        products = result.data.influencers?.products
                                    )
                                    val allData = listOf(allInfluencer)
                                    addInfluencerData(allData)
                                }
                                is Result.Error -> {
                                    Log.e("error msg", result.error)
                                    binding.progressBar.visibility = View.GONE
                                    binding.warningCont.visibility = View.VISIBLE
                                    when{
                                        result.error.trim() == "HTTP 401" -> {
                                            binding.error.visibility = View.VISIBLE
                                        }
                                        result.error.trim() == "HTTP 404" -> {
                                            binding.error.setText(R.string.no_influencer)
                                        }
                                    }
                                }
                            }
                        }
                    }
//                addInfluencerData
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
    }

    private fun setupViewModel() {
        searchViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity().application)
        )[SearchViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.refreshBtn.setOnClickListener{
            getAllInfluencer()
        }

        searchQueue()
        getAllInfluencer()
    }

    private fun getAllInfluencer(){
        searchViewModel.getInfluencrs().observe(viewLifecycleOwner){result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.warningCont.visibility = View.GONE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.warningCont.visibility = View.GONE
                    Log.e("test data", result.data.influencers.toString())
                    val allInfluencer: List<InfluencersItem> = result.data.influencers as List<InfluencersItem>
                    addInfluencerData(allInfluencer)
                }
                is Result.Error -> {
                    Log.e("error msg", result.error)
                    binding.progressBar.visibility = View.GONE
                    binding.warningCont.visibility = View.VISIBLE
                    when{
                        result.error.trim() == "HTTP 401" -> {
                            binding.error.visibility = View.VISIBLE
                        }
                        result.error.trim() == "HTTP 404" -> {
                            binding.error.setText(R.string.no_influencer)
                        }
                    }
                }
            }
        }
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
        Log.e("test rate", influencerData.rating.toString())
        moveIntent.putExtra("rating",influencerData.rating.toString())
        startActivity(moveIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}