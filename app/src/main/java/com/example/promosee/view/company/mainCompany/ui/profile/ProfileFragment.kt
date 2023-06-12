package com.example.promosee.view.company.mainCompany.ui.profile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.promosee.R
import com.example.promosee.databinding.FragmentProfileBinding
import com.example.promosee.model.Result
import com.example.promosee.model.remote.retrofit.ApiConfig
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.company.mainCompany.ui.notifications.NotificationsFragment
import com.example.promosee.view.login.LoginActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private lateinit var profileViewModel: ProfileViewModel

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

        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        setupViewModel()
        binding.logout.setOnClickListener{
            logout()
        }
        binding.pesananSaya.setOnClickListener {
            val notificationsFragment = NotificationsFragment()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.nav_host_fragment_activity_main_com, notificationsFragment, NotificationsFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
        binding.companyName.text = ApiConfig.USERNAME
        val root: View = binding.root
        return root
    }

    private fun logout() {
        profileViewModel.logout().observe(viewLifecycleOwner){result ->
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

    private fun setupViewModel() {
        profileViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity().application)
        )[ProfileViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}