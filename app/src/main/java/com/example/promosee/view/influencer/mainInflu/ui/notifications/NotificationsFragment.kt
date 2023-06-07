package com.example.promosee.view.influencer.mainInflu.ui.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promosee.adapter.OrderAdapter
import com.example.promosee.databinding.FragmentNotificationsBinding
import com.example.promosee.model.local.preference.DummyOrders
import com.example.promosee.model.local.preference.OrderModel

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {

    }

    private fun setupAction() {
        addOrdersData(DummyOrders.listOrders)
    }

    private fun addOrdersData(allOrders: List<OrderModel>) {
        Log.d("hai", allOrders.size.toString())

        // membuat jumlah kolom dalam grid
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvOrder.layoutManager = linearLayoutManager

//        // memasukkan data ke adapter
//        val orderAdapter = OrderAdapter(allOrders)
//        binding.rvOrder.adapter = orderAdapter
//
//        Log.d("Count", orderAdapter.itemCount.toString())
//
//        orderAdapter.setOnItemClickCallback(object : OrderAdapter.OnItemClickCallback {
//            override fun onItemClicked(orderData: OrderModel) {
//                Log.e("test item", orderData.toString())
//            }
//        })

        binding.progressBar.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}