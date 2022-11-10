
package com.stefanus.android.marsphotos.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.marsphotos.databinding.FragmentOverviewBinding

/**
 * Fragmen ini menunjukkan status transaksi layanan web foto Mars.
 */
class OverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    /**
     * digunakan untuk mengembangkan tata letak dengan data binding, menentapkan lifecycle ke OverviewFragment
     * untuk aktifkan  Data Binding ke observe LiveData, dan menyiapkan RecyclerView dengan adapter.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentOverviewBinding.inflate(inflater)

        // mengizinkan Data Binding melihat LiveData dengan lifecycle Fragment ini
        binding.lifecycleOwner = this

        // memberikan akses data binding ke OverviewViewModel
        binding.viewModel = viewModel

        // menyiapkan adapter photosGrid RecyclerView
        binding.photosGrid.adapter = PhotoGridAdapter()

        return binding.root
    }
}
