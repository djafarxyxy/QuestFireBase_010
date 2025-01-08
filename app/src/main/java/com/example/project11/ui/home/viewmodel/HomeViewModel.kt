package com.example.project11.ui.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import com.example.project11.model.Mahasiswa
import com.example.project11.repository.RepositoryMhs
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {
    var mhsUiState: HomeuiState by mutableStateOf((HomeuiState.Loading))
        private set

    init {
        getMhs()
    }

    fun getMhs(){
        viewModelScope.launch {
            repositoryMhs.getAllMhs()
                .onStart {
                    mhsUiState = HomeuiState.Loading
                }
                .catch {
                    mhsUiState = HomeuiState.Error(e = it)
                }
                .collect{
                    mhsUiState = if (it.isEmpty()) {
                        HomeuiState.Error(Exception("Belum ada data mahasiswa"))
                    } else {
                        HomeuiState.Success(it)
                    }
                }
        }
    }
}


sealed class HomeuiState{
    object Loading : HomeuiState()

    data class Success(val data: List<Mahasiswa>) : HomeuiState()

    data class Error(val e: Throwable) : HomeuiState()
}