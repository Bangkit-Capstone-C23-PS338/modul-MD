package com.example.promosee.view.company.mainCompany.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promosee.R
import com.example.promosee.adapter.GridAdapter
import com.example.promosee.adapter.GridSpacingItemDecoration
import com.example.promosee.adapter.OrderAdapter
import com.example.promosee.databinding.FragmentHomeBinding
import com.example.promosee.model.Result
import com.example.promosee.model.local.preference.DummyOrders
import com.example.promosee.model.local.preference.OrderModel
import com.example.promosee.model.remote.reponse.InfluencersItem
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.ui.detailInfluencer.InfluencerDetailActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupAction()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupAction() {
        homeViewModel.getUser().observe(requireActivity()){ user ->
            binding.helloUser.text = getString(R.string.hello_user, user.username)
        }
        addOrdersData(DummyOrders.listOrders)
        homeViewModel.getInfluencers().observe(requireActivity()){result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("test data", result.data.influencers.toString())
                    val allInfluencer: List<InfluencersItem> = result.data.influencers as List<InfluencersItem>
                    addInfluencerData(allInfluencer)
                }
                is Result.Error -> {}
            }
        }

    }
    private fun setupViewModel() {
        homeViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        )[HomeViewModel::class.java]
    }

    private fun addInfluencerData(allInfluencer: List<InfluencersItem>) {

        // membuat jumlah kolom dalam grid
        val gridLayoutManager = GridLayoutManager(requireContext(), 1, GridLayoutManager.HORIZONTAL, false)
        binding.rvReccomendation.layoutManager = gridLayoutManager

        // membuat jarak antar item dengan satuan dp
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_5dp)
        val includeEdge = false

        // mengset jarak antar item
        binding.rvReccomendation.addItemDecoration(GridSpacingItemDecoration(1, spacingInPixels, includeEdge))

        // memasukkan data ke adapter
        val adapter = GridAdapter(allInfluencer)
        binding.rvReccomendation.adapter = adapter

        adapter.setOnItemClickCallback(object : GridAdapter.OnItemClickCallback {
            override fun onItemClicked(influencerData: InfluencersItem) {
                Log.e("test item", influencerData.igUsername.toString())
                showSelectedInfluencer(influencerData)
            }
        })

    }

    private fun addOrdersData(allOrders: List<OrderModel>) {
        Log.d("hai", allOrders.size.toString())

        // membuat jumlah kolom dalam grid
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvOrder.layoutManager = linearLayoutManager

        // memasukkan data ke adapter
        val orderAdapter = OrderAdapter(allOrders)
        binding.rvOrder.adapter = orderAdapter

        Log.d("Count", orderAdapter.itemCount.toString())

        orderAdapter.setOnItemClickCallback(object : OrderAdapter.OnItemClickCallback {
            override fun onItemClicked(orderData: OrderModel) {
                Log.e("test item", orderData.toString())
            }
        })

        binding.progressBar.visibility = View.GONE
    }

    private fun showSelectedInfluencer(influencerData: InfluencersItem) {
        val moveIntent = Intent(requireContext(), InfluencerDetailActivity::class.java)
        moveIntent.putExtra("username",influencerData.username)
        startActivity(moveIntent)
    }
}