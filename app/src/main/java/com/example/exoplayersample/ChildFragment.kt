package com.example.exoplayersample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exoplayersample.databinding.FragmentChildBinding
import com.example.exoplayersample.epoxy.EpoxyController
import com.example.exoplayersample.epoxy.EpoxyDataList

class ChildFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentChildBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val epoxyController = EpoxyController().also {
            it.setData(EpoxyDataList())
        }
        binding.recyclerView.also {
            it.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
            it.adapter = epoxyController.adapter
        }
    }

    companion object {
        private const val TAG = "ChildFragment"
    }
}