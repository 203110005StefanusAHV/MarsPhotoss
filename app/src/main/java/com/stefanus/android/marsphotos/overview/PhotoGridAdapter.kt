package com.stefanus.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.stefanus.android.marsphotos.network.MarsPhoto

/**
 * kelas untuk mengimplementasikan [RecyclerView] [ListAdapter] yang menggunakan Binding
 * untuk menyajikan [List] data, termasuk menghitung perbedaan antara daftar.
 */
class PhotoGridAdapter :
    ListAdapter<MarsPhoto, PhotoGridAdapter.MarsPhotosViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder konstruktor mengambil variabel binding pengikat dari yang terkait
     * GridViewItem, dan memberikannya akses penuh [MarsPhoto].
     */
    class MarsPhotosViewHolder(
        private var binding: GridViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(marsPhoto: MarsPhoto) {
            binding.photo = marsPhoto
            // hal ini perlu diperhatikan, karena untuk segera dieksekusi data binding,
            // yang memungkinkan RecyclerView membuat pengukuran ukuran tampilan yang benar
            binding.executePendingBindings()
        }
    }

    /**
     * Memungkinkan RecyclerView untuk menentukan item mana yang telah berubah
     * ketika [List] dari [MarsPhoto] telah diperbarui.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhoto>() {
        override fun areItemsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhoto, newItem: MarsPhoto): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }

    /**
     * membuat tampilan item baru [RecyclerView] (dipanggil dari layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarsPhotosViewHolder {
        return MarsPhotosViewHolder(
            GridViewItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * mengganti konten (dipanggil dari layout manager)
     */
    override fun onBindViewHolder(holder: MarsPhotosViewHolder, position: Int) {
        val marsPhoto = getItem(position)
        holder.bind(marsPhoto)
    }
}
