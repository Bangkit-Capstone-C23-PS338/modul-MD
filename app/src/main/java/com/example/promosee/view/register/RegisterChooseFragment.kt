package com.example.promosee.view.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.promosee.R
import com.example.promosee.databinding.FragmentRegisterChooseBinding

class RegisterChooseFragment : Fragment() {
    private var _binding: FragmentRegisterChooseBinding? = null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterChooseBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnToCompany.setOnClickListener {
            val companyFragment = RegisterCompanyFragment()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.frame_container, companyFragment, RegisterCompanyFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
        binding.btnToInfluencer.setOnClickListener {
            val influencerFragment = RegisterInfluencerFragment()
            val fragmentManager = parentFragmentManager
            fragmentManager.beginTransaction().apply {
                replace(R.id.frame_container, influencerFragment, RegisterInfluencerFragment::class.java.simpleName)
                addToBackStack(null)
                commit()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}