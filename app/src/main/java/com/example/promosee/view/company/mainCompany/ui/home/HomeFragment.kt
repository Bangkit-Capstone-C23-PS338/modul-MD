package com.example.promosee.view.company.mainCompany.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promosee.R
import com.example.promosee.adapter.GridAdapter
import com.example.promosee.adapter.GridSpacingItemDecoration
import com.example.promosee.adapter.OrderAdapter
import com.example.promosee.databinding.FragmentHomeBinding
import com.example.promosee.model.Result
import com.example.promosee.model.remote.reponse.InfluencersItem
import com.example.promosee.model.remote.reponse.OrderItem
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.ui.detailInfluencer.InfluencerDetailActivity
import com.example.promosee.view.company.mainCompany.ui.notifications.NotificationsFragment
import com.example.promosee.view.company.mainCompany.ui.order.OrderDetailActivity
import com.example.promosee.view.company.mainCompany.ui.search.SearchFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel

    private var type: String = ""
    private var username: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
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

        binding.fullReccomendation.setOnClickListener {
            val searchFragment = SearchFragment()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main_com, searchFragment, SearchFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
        binding.fullOrder.setOnClickListener {
            val notificationsFragment = NotificationsFragment()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main_com, notificationsFragment, NotificationsFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }

        homeViewModel.getUser().observe(viewLifecycleOwner){ user ->
            username = user.username
            type = user.user_access
            homeViewModel.getCompanyOrders(username).observe(viewLifecycleOwner){result ->
                when(result){
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val allOrders: List<OrderItem> = result.data.orders.take(5)
                        addOrdersData(allOrders)
                    }
                    is Result.Error -> {}
                    else -> {}
                }
            }
            binding.helloUser.text = getString(R.string.hello_user, user.username)
        }

        homeViewModel.getInfluencers().observe(viewLifecycleOwner){result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Log.e("test data", result.data.influencers.toString())
                    val allInfluencer: List<InfluencersItem> = result.data.influencers?.take(5) as List<InfluencersItem>
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

    private fun addOrdersData(allOrders: List<OrderItem>) {
        // membuat jumlah kolom dalam grid
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvOrder.layoutManager = linearLayoutManager

        // memasukkan data ke adapter
        val orderAdapter = OrderAdapter(allOrders)
        orderAdapter.checkTokenCompany(true)
        binding.rvOrder.adapter = orderAdapter
        orderAdapter.setOnItemClickCallback(object : OrderAdapter.OnItemClickCallback {
            override fun onItemClicked(order: OrderItem) {
                val intentToDetail = Intent(requireContext(), OrderDetailActivity::class.java)
                intentToDetail.putExtra(OrderDetailActivity.EXTRA_ORDER_ID, order.order_id)
                startActivity(intentToDetail)
            }
        })
    }

    private fun showSelectedInfluencer(influencerData: InfluencersItem) {
        val moveIntent = Intent(requireContext(), InfluencerDetailActivity::class.java)
        moveIntent.putExtra("username",influencerData.username)
        startActivity(moveIntent)
    }
}