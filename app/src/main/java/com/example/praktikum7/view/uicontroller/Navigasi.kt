package com.example.praktikum7.view.uicontroller

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.praktikum7.model.dataJK.JenisK
import com.example.praktikum7.viewmodel.SiswaViewModel

enum class Navigasi {
    Formulir,
    Detail
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiswaApp(
    modifier : Modifier,
    viewModel: SiswaViewModel = viewModel(),
) {
    Scaffold { isiRuang->
        val uiState = viewModel.statusUI.collectAsState()
        navHost(
            navController = navController,
            startDestination = Navigasi.Formulir.name,

            modifier = Modifier.padding(paddingValues = isiRuang)){
            composable(route = Navigasi.Formulir.name){
                val konteks = LocalContext.current
                FormSiswa(
                    pilihanJK = JenisK.map {id -> konteks.resources.getString(id)},
                    onSubmitButtonClicked = {
                        viewModel.setSiswa(it)
                        navController.navigate(route = Navigasi.Detail.name)
                    }
                )
            }





        }
