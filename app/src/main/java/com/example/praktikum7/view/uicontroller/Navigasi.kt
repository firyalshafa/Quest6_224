package com.example.praktikum7.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.praktikum7.model.dataJK.JenisK
import com.example.praktikum7.viewmodel.SiswaViewModel
//import com.example.praktikum7.view.FormSiswa
//import com.example.praktikum7.view.TampilSiswa
import com.example.praktikum7.model.Siswa // <--- Perlu diimpor untuk mendefinisikan tipe Siswa

enum class Navigasi {
    Formulir,
    Detail
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaApp(
    modifier: Modifier = Modifier,
    viewModel: SiswaViewModel = viewModel(),
) {
    val navController = rememberNavController()
    val uiState = viewModel.statusUI.collectAsState()

    Scaffold { isiRuang ->

        NavHost(
            navController = navController,
            startDestination = Navigasi.Formulir.name,
            modifier = Modifier.padding(isiRuang)
        ) {

            composable(Navigasi.Formulir.name) {
                val konteks = LocalContext.current
                FormSiswa(
                    pilihanJK = JenisK.map { id -> konteks.resources.getString(id) },
                    onSubmitButtonClicked = { dataSiswa: Siswa -> // PERBAIKAN: Deklarasi tipe Siswa
                        viewModel.setSiswa(dataSiswa)
                        navController.navigate(route = Navigasi.Detail.name)
                    }
                )
            }

            composable(route = Navigasi.Detail.name) {
                TampilSiswa(
                    statusUISiswa = uiState.value, // Sudah diperbaiki dari statusUiSiswa
                    onBackButtonClicked = {
                        cancelAndBackToFormulir(navController)
                    }
                )
            }
        }
    }
}

private fun cancelAndBackToFormulir(
    navController: NavHostController
) {
    navController.popBackStack(
        route = Navigasi.Formulir.name,
        inclusive = false
    )
}