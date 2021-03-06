package com.borshevskiy.cryptoapprefactoring.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.borshevskiy.cryptoapprefactoring.CryptoApp
import com.borshevskiy.cryptoapprefactoring.databinding.FragmentCoinPriceListBinding
import com.borshevskiy.cryptoapprefactoring.presentation.adapters.CoinInfoAdapter
import javax.inject.Inject

class CoinPriceListFragment : Fragment() {

    private var _binding: FragmentCoinPriceListBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mAdapter by lazy { CoinInfoAdapter(requireContext()) }
    private val component by lazy { (requireActivity().application as CryptoApp).component}

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
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
        val coinViewModel = ViewModelProvider(this,viewModelFactory)[CoinViewModel::class.java]
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