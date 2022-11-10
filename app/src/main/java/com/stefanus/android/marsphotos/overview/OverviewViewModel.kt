package com.stefanus.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stefanus.android.marsphotos.network.MarsApi
import com.stefanus.android.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }

/**
 * [ViewModel] yang terdapat pada [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // MutableLiveData yang di dalamnya mentimpan status untuk meminta permintaan baru
    private val _status = MutableLiveData<MarsApiStatus>()

    // LiveData eksternal yang tidak dapat diubah untuk status permintaan baru
    val status: LiveData<MarsApiStatus> = _status

    // didalamnya menggunakan MutableLiveData, karena untuk memperbaharui dalam MarsPhoto
    private val _photos = MutableLiveData<List<MarsPhoto>>()

    // antarmuka LiveData ekternal tidak dapat diubah, jadi di kelas ini yang dapat memodifikasinya
    val photos: LiveData<List<MarsPhoto>> = _photos

    /**
     * memanggil getMarsPhotos() pada init sehingga dengan ini dapat menampilkan status
     */
    init {
        getMarsPhotos()
    }

    /**
     * Mendapatkan informasi foto Mars dari layanan Mars API Retrofit dan
     * memperbarui [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {

        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _photos.value = MarsApi.retrofitService.getPhotos()
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MarsApiStatus.ERROR
                _photos.value = listOf()
            }
        }
    }
}
