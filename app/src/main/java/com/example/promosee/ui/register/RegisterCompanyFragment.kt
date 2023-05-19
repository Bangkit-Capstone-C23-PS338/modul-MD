package com.example.promosee.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.promosee.R


/**
 * A simple [Fragment] subclass.
 * Use the [RegisterCompanyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterCompanyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_company, container, false)
    }

}