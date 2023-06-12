package com.example.promosee.view.influencer.mainInflu.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.promosee.R
import com.example.promosee.databinding.FragmentProfileBinding
import com.example.promosee.databinding.FragmentProfileInfluencerBinding
import com.example.promosee.model.Result
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.ui.profile.ProfileViewModel
import com.example.promosee.view.influencer.mainInflu.ui.product.ProductActivity
import com.example.promosee.view.login.LoginActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileInfluencerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileInfluencerFragment : Fragment() {


    private lateinit var profileInfluencerViewModel: ProfileInfluencerViewModel
    private var _binding: FragmentProfileInfluencerBinding? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        _binding = FragmentProfileInfluencerBinding.inflate(inflater, container, false)
        setupViewModel()
        setupAction()
        binding.logout.setOnClickListener{
            logout()
        }
        binding.pesananSaya.setOnClickListener {
            val moveIntent = Intent(requireContext(), ProductActivity::class.java)
            startActivity(moveIntent)
        }

        val root: View = binding.root
        return root
    }

    private fun setupAction() {
        profileInfluencerViewModel.getProfile().observe(viewLifecycleOwner){result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.influencerName.text = result.data.influencers?.username ?: ""
                    binding.emailInfluencer.text = result.data.influencers?.email ?: ""
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

    private fun setupViewModel() {
        profileInfluencerViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity().application)
        )[ProfileInfluencerViewModel::class.java]
    }

    private fun logout() {
        profileInfluencerViewModel.logout().observe(viewLifecycleOwner){result ->
            when(result){
                is Result.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val intent =  Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
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

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileInfluencerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}