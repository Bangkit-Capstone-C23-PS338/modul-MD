package com.example.promosee.view.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.promosee.R
import com.example.promosee.databinding.FragmentRegisterInfluencerBinding
import com.example.promosee.model.Result
import com.example.promosee.model.local.preference.CompanyModel
import com.example.promosee.model.local.preference.InfluencerModel
import com.example.promosee.view.ViewModelFactory
import com.example.promosee.view.login.LoginActivity

class RegisterInfluencerFragment : Fragment() {
    private var _binding: FragmentRegisterInfluencerBinding? = null
    private val binding get() =_binding!!
    private lateinit var registerViewModel: RegisterViewModel
    private var isValidated: Boolean = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterInfluencerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        registerViewModel = ViewModelProvider(
            this,
            ViewModelFactory.getInstance(requireActivity())
        )[RegisterViewModel::class.java]
    }

    private fun setupAction() {
        binding.apply {
            toLogin.setOnClickListener {
                val intentToLogin = Intent(requireActivity(), LoginActivity::class.java)
                startActivity(intentToLogin)
            }
            btnRegister.setOnClickListener {
                runValidation()
                if (isValidated){
                    val listCategories: MutableList<String> = mutableListOf()
                    if(checkboxFood.isChecked){
                        listCategories.add(getString(R.string.food))
                    }
                    if(checkboxHealth.isChecked){
                        listCategories.add(getString(R.string.health))
                    }
                    if(checkboxSports.isChecked){
                        listCategories.add(getString(R.string.sports))
                    }
                    if(checkboxTech.isChecked){
                        listCategories.add(getString(R.string.technology))
                    }
                    if(checkboxFashion.isChecked){
                        listCategories.add(getString(R.string.beauty_and_fashion))
                    }
                    if(checkboxTravel.isChecked){
                        listCategories.add(getString(R.string.travel))
                    }
                    if(checkboxGame.isChecked){
                        listCategories.add(getString(R.string.gaming))
                    }
                    if(checkboxEntertainment.isChecked){
                        listCategories.add(getString(R.string.entertainment))
                    }
                    val listFixCategories: List<String> = listCategories

                    val influencer = InfluencerModel(
                        editTextTextUsername.text.toString(),
                        editTextTextEmailAddress3.text.toString(),
                        editTextTextPassword.text.toString(),
                        listFixCategories
                    )
                    registerViewModel.setInfluencer(influencer)
                    registerViewModel.registerInfluencer().observe(viewLifecycleOwner){result ->
                        when(result){
                            is Result.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.btnRegister.isEnabled = false
                            }
                            is Result.Success -> {
                                binding.btnRegister.isEnabled = true
                                binding.progressBar.visibility = View.GONE
                                val msg: String = getString(R.string.account_registered)
                                Log.e("test data", result.data.toString())
                                Toast.makeText(
                                    requireActivity(),
                                    msg,
                                    Toast.LENGTH_SHORT
                                ).show()
                                // intent ke halaman utama, dan meriset intent
                                val intent = Intent(requireActivity(), LoginActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                                startActivity(intent)
                            }
                            is Result.Error -> {
                                binding.btnRegister.isEnabled = true
                                binding.progressBar.visibility = View.GONE
                                val msg: String = getString(R.string.register_failed)
                                Toast.makeText(
                                    requireActivity(),
                                    msg,
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                    }
                }
            }
        }
    }

    private fun runValidation() {
        isValidated = true
        binding.apply {
            if(editTextTextUsername.text?.isEmpty() as Boolean){
                usernameEditTextLayout.error = getString(R.string.username_empty)
                isValidated = false
            } else{
                usernameEditTextLayout.error = null
            }
            if(editTextTextEmailAddress3.text?.isEmpty() as Boolean){
                emailEditTextLayout.error = getString(R.string.email_empty)
                isValidated = false
            } else{
                emailEditTextLayout.error = null
            }
            if(editTextTextPassword.text.toString() != editTextTextPasswordConfirmation.text.toString()){
                passwordConfirmationEditTextLayout.error = getString(R.string.pass_must_match)
                isValidated = false
            } else{
                passwordConfirmationEditTextLayout.error = null
            }
            if(editTextTextPassword.text.toString().length < 8){
                passwordEditTextLayout.error = getString(R.string.minimum_of_8_characters)
                isValidated = false
            } else{
                passwordEditTextLayout.error = null
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}