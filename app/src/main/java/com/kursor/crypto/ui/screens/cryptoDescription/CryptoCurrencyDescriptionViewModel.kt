package com.kursor.crypto.ui.screens.cryptoDescription

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kursor.crypto.ConnectionStatus
import com.kursor.crypto.domain.useCases.LoadCryptoCurrencyDescriptionUseCase
import com.kursor.crypto.model.entities.CryptoCurrencyDescription
import kotlinx.coroutines.launch

class CryptoCurrencyDescriptionViewModel(
    val loadCryptoCurrencyDescriptionUseCase: LoadCryptoCurrencyDescriptionUseCase
) : ViewModel() {

    private val _cryptoCurrencyDescriptionLiveData = MutableLiveData<CryptoCurrencyDescription>()
    val cryptoCurrencyDescriptionLiveData: LiveData<CryptoCurrencyDescription> get() = _cryptoCurrencyDescriptionLiveData

    private val _connectionStatusLiveData = MutableLiveData(ConnectionStatus.LOADING)
    val connectionStatusLiveData: LiveData<ConnectionStatus> get() = _connectionStatusLiveData

    fun loadData(id: String) {
        _connectionStatusLiveData.value = ConnectionStatus.LOADING
        viewModelScope.launch {
            loadCryptoCurrencyDescriptionUseCase(id)
                .onSuccess {
                    _cryptoCurrencyDescriptionLiveData.postValue(it)
                    _connectionStatusLiveData.postValue(ConnectionStatus.SUCCESS)
                }.onFailure {
                    _connectionStatusLiveData.postValue(ConnectionStatus.FAILURE)
                }
        }
    }

}