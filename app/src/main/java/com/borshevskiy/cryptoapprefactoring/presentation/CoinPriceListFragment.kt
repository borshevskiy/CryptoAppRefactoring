package com.borshevskiy.cryptoapprefactoring.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.borshevskiy.cryptoapprefactoring.databinding.FragmentCoinPriceListBinding
import com.borshevskiy.cryptoapprefactoring.presentation.adapters.CoinInfoAdapter

class CoinPriceListFragment : Fragment() {

    private var _binding: FragmentCoinPriceListBinding? = null
    private val binding get() = _binding!!

    private lateinit var coinViewModel: CoinViewModel
    private val mAdapter by lazy { CoinInfoAdapter(requireContext()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        coinViewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinPriceListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rvCoinPriceList.adapter = mAdapter
        coinViewModel.coinInfoList.observe(viewLifecycleOwner) {
            mAdapter.submitList(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}