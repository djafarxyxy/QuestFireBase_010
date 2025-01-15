package com.example.project11.ui.home.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project11.ui.PenyediaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    nim: String,
    onEditClick: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val mahasiswa = viewModel.uiState.detailUiEvent

    LaunchedEffect(nim) {
        viewModel.fetchDetailMahasiswa(nim)
    }

    val isLoading = viewModel.uiState.isLoading
    val isError = viewModel.uiState.isError
    val errorMessage = viewModel.uiState.errorMessage

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(DestinasiDetail.titleRes) },
                navigationIcon = {
                    IconButton(onClick = { onBackClick() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                } else if (isError) {
                    Text(
                        text = errorMessage,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else if (viewModel.uiState.isUiEventNotEmpty) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(text = "NIM: ${mahasiswa.nim}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Nama: ${mahasiswa.nama}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Alamat: ${mahasiswa.alamat}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Jenis Kelamin: ${mahasiswa.jenis_Kelamin}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Kelas: ${mahasiswa.kelas}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "Angkatan: ${mahasiswa.angkatan}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "JudulSkripsi: ${mahasiswa.JudulSkripsi}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "DosenPembimbing1: ${mahasiswa.DosenPembimbing1}", style = MaterialTheme.typography.bodyLarge)
                                Text(text = "DosenPembimbing2: ${mahasiswa.DosenPembimbing2}", style = MaterialTheme.typography.bodyLarge)
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Button(onClick = { onEditClick(mahasiswa.nim) }) {
                                Text("Edit Data")
                            }
                        }
                    }
                }
            }
        }
    )
}