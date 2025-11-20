package com.example.praktikum7.view.uicontroller

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api // Diperlukan untuk TopAppBar
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable // Wajib
import androidx.compose.runtime.getValue // Wajib
import androidx.compose.runtime.mutableStateOf // Wajib
import androidx.compose.runtime.saveable.rememberSaveable // Wajib
import androidx.compose.runtime.setValue // Wajib
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.praktikum7.R // Perbaikan impor R yang benar
import com.example.praktikum7.model.Siswa // Asumsi lokasi data class Siswa

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormSiswa(
    pilihanJK: List<String>, // Parameter untuk pilihan jenis kelamin
    onSubmitButtonClicked: (Siswa) -> Unit // Parameter untuk aksi submit
) {
    // Deklarasi State untuk Input Teks
    var txtNama by rememberSaveable { mutableStateOf("") }
    var txtGender by rememberSaveable { mutableStateOf("") }
    var txtAlamat by rememberSaveable { mutableStateOf("") }

    // Objek Siswa yang akan dikirim
    val dataSiswa = Siswa(
        nama = txtNama,
        gender = txtGender,
        alamat = txtAlamat
    )

    Scaffold(modifier = Modifier,
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.app_name), color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = colorResource(id = R.color.purple_500)),
            )
        }
    ) { isiRuang ->

        Column(
            modifier = Modifier.padding(paddingValues = isiRuang),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //edit 3: value, onValueChange, selected, onClick
            OutlinedTextField(
                value = txtNama, // Menggunakan state txtNama
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.width(250.dp).padding(top = 20.dp),
                label = { Text(text = "Nama Lengkap") },
                onValueChange = {
                    txtNama = it // Mengubah state txtNama
                }
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(all = 12.dp)
                    .width(250.dp),
                thickness = dimensionResource(id = R.dimen.dimen_1dp),
                color = Color.Blue
            )

            Row {
                pilihanJK.forEach { item ->

                    Row(modifier = Modifier.selectable(
                        selected = txtGender == item,
                        onClick = {
                            txtGender = item
                        }
                    ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = txtGender == item,
                            onClick = {
                                txtGender = item
                            }
                        )
                        Text(text = item)
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(all = 5.dp)
                    .width(width = 250.dp),
                thickness = dimensionResource(id = R.dimen.dimen_1dp),
                color = Color.Blue
            )

            OutlinedTextField(
                value = txtAlamat, // Menggunakan state txtAlamat
                singleLine = true,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.width(250.dp),
                label = { Text(text = "Alamat Lengkap") },
                onValueChange = {
                    txtAlamat = it // Mengubah state txtAlamat
                }
            )

            Spacer(Modifier.height(height = 20.dp))
            Button (
                modifier = Modifier.fillMaxWidth(fraction = 1f),
                enabled = txtAlamat.isNotEmpty(),
                onClick = { onSubmitButtonClicked(dataSiswa) } // Mengirim objek dataSiswa
            ) {
                Text(text = stringResource(id = R.string.submit)) // Asumsi "Submit" ada di strings.xml
            }
        }
    }
}