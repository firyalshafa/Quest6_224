package com.example.praktikum7.viewmodel


import android.R
import androidx.lifecycle.ViewModel
import com.example.praktikum7.model.Siswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SiswaViewModel : ViewModel() {
    private val _statusUI = MutableStateFlow(Siswa())

    val statusUI: StateFlow<Siswa> = _statusUI.asStateFlow()

    // Perbaikan di SiswaViewModel.kt
    fun setSiswa(siswaBaru: Siswa) { // Ubah tipe dari MutableList<String> menjadi Siswa
        _statusUI.update { statusSaatIni ->
            // Langsung salin properti dari objek Siswa yang diterima
            statusSaatIni.copy(
                nama = siswaBaru.nama,
                gender = siswaBaru.gender,
                alamat = siswaBaru.alamat
            )
        }
    }}