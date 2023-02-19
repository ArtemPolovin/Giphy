package com.example.giphy.ui.gif_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.giphy.ResponseResult
import com.example.giphy.databinding.FragmentGifBinding
import com.example.giphy.ui.gif_list.adapter.GifAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Gif : Fragment() {

    private var _binding: FragmentGifBinding? = null
    private val binding: FragmentGifBinding
        get() = _binding ?: throw RuntimeException("FragmentGifBinding is null")

    private val viewModel: GifViewModel by viewModels()
    private lateinit var adapterGif: GifAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapterGif = GifAdapter()
        _binding = FragmentGifBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupGifList()
        onReloadPage()

    }

    private fun setupRecyclerView() {
        binding.rvGifs.run{
            adapter = adapterGif
            layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupGifList() {
        viewModel.gifList.observe(viewLifecycleOwner){
            binding.errorGroup.visibility = View.GONE
            binding.rvGifs.visibility = View.GONE

            when (it) {
                is ResponseResult.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }
                is ResponseResult.Failure ->{
                    binding.textError.visibility = View.VISIBLE
                    binding.textError.text = it.message
                    binding.btnRetry.visibility = View.VISIBLE
                }
                is ResponseResult.Success ->{
                    binding.rvGifs.visibility = View.VISIBLE
                    adapterGif.submitList(it.data)
                }
            }
        }
    }

    private fun onReloadPage() {
        binding.btnRetry.setOnClickListener {
            viewModel.reloadGifs()
        }
    }
}