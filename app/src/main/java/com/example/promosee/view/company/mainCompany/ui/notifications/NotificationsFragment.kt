package com.example.promosee.view.company.mainCompany.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promosee.adapter.GridAdapter
import com.example.promosee.adapter.OrderAdapter
import com.example.promosee.databinding.FragmentNotificationsBinding
import com.example.promosee.model.Result
import com.example.promosee.model.remote.reponse.InfluencersItem
import com.example.promosee.model.remote.reponse.OrderItem
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.ui.order.OrderDetailActivity

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var notificationsViewModel: NotificationsViewModel
    private var type: String = ""
    private var username: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupView()
        setupAction()
    }


    private fun setupViewModel() {
        notificationsViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        )[NotificationsViewModel::class.java]
    }
    private fun setupView() {

    }

    private fun setupAction() {
        notificationsViewModel.getUser().observe(viewLifecycleOwner){
            username = it.username
            type = it.user_access
            notificationsViewModel.getCompanyOrders(username).observe(viewLifecycleOwner){result ->
                when(result){
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val allOrders: List<OrderItem> = result.data.orders
                        addOrdersData(allOrders)
                    }
                    is Result.Error -> {}
                }
            }
        }
    }

    private fun addOrdersData(allOrders: List<OrderItem>) {
        // membuat jumlah kolom dalam grid
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}