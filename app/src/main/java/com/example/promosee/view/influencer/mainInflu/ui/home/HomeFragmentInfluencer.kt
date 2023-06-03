package com.example.promosee.view.influencer.mainInflu.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.promosee.R
import com.example.promosee.adapter.ProductAdapter
import com.example.promosee.databinding.FragmentHomeInfluencerBinding
import com.example.promosee.databinding.FragmentProfileInfluencerBinding
import com.example.promosee.model.Result
import com.example.promosee.model.remote.reponse.ProductsItemInfluencer
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.influencer.mainInflu.ui.profile.ProfileInfluencerViewModel
import com.example.promosee.view.login.LoginActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragmentInfluencer.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragmentInfluencer : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var homeInfluencerViewModel: HomeInfluencerViewModel
    private var _binding: FragmentHomeInfluencerBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeInfluencerBinding.inflate(inflater, container, false)
        setupViewmodel()
        setupAction()

        // Inflate the layout for this fragment
        val root: View = binding.root
        return root
    }

    private fun setupViewmodel() {
        homeInfluencerViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity().application)
        )[HomeInfluencerViewModel::class.java]
    }

    private fun setupAction() {
        homeInfluencerViewModel.getInfluencrProducts().observe(viewLifecycleOwner){result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val allInfluencerProduct: List<ProductsItemInfluencer> = result.data.products as List<ProductsItemInfluencer>
                    if(allInfluencerProduct.isNotEmpty()){
                        addInfluencerProduct(allInfluencerProduct)
                    }
                }
                is Result.Error -> {
                    Log.e("error msg", result.error)
                    if(result.error.trim() == "HTTP 401"){
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun addInfluencerProduct(products: List<ProductsItemInfluencer>) {

        // sambungan ke adapter
        binding.rvProduct.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = ProductAdapter(products,"influencer")
        binding.rvProduct.adapter = adapter

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragmentInfluencer().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}