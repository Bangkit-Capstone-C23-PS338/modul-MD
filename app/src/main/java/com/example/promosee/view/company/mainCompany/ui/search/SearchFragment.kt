package com.example.promosee.view.company.mainCompany.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.promosee.R
import com.example.promosee.adapter.GridAdapter
import com.example.promosee.adapter.GridSpacingItemDecoration
import com.example.promosee.databinding.FragmentSearchBinding
import com.example.promosee.model.local.preference.InfluencerModel

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        dashboardViewModel.text.observe(viewLifecycleOwner) {
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val catDum = listOf<String>("kpop")
        val dummyInfluecers: List<InfluencerModel> = listOf(
            InfluencerModel(
                username = "Lee Je Eun",
                email = "ben@gmail.com",
                password = "123",
                categories = catDum,
            ),
            InfluencerModel(
                username = "Lee Je Eun",
                email = "ben@gmail.com",
                password = "123",
                categories = catDum,
            ),
            InfluencerModel(
                username = "Lee Je Eun",
                email = "ben@gmail.com",
                password = "123",
                categories = catDum,
            ),
            InfluencerModel(
                username = "Lee Je Eun",
                email = "ben@gmail.com",
                password = "123",
                categories = catDum,
            ),
            InfluencerModel(
                username = "Lee Je Eun",
                email = "ben@gmail.com",
                password = "123",
                categories = catDum,
            )
        )

        addInfluencerData(dummyInfluecers)
    }

    private fun addInfluencerData(influencerModels: List<InfluencerModel>) {

        // membuat jumlah kolom dalam grid
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewRecom.layoutManager = gridLayoutManager

        // membuat jarak antar item dengan satuan dp
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.spacing_5dp)
        val includeEdge = false

        // mengset jarak antar item
        binding.recyclerViewRecom.addItemDecoration(GridSpacingItemDecoration(2, spacingInPixels, includeEdge))

        // memasukkan data ke adapter
        val adapter = GridAdapter(influencerModels)
        binding.recyclerViewRecom.adapter = adapter

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}