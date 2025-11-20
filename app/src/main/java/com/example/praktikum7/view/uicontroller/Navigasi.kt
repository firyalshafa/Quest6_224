package com.example.praktikum7.view.uicontroller

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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


    }
