package com.kursor.crypto.ui.screens.cryptoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursor.crypto.ConnectionStatus
import com.kursor.crypto.domain.useCases.LoadCryptoCurrencyInfoListUseCase
import com.kursor.crypto.model.entities.CryptoCurrencyInfo
import kotlinx.coroutines.launch

class CryptoCurrencyInfoListViewModel(
    val loadCryptoCurrencyInfoListUseCase: LoadCryptoCurrencyInfoListUseCase
) : ViewModel() {

    private val _cryptoCurrencyInfoListLiveData =
        MutableLiveData<List<CryptoCurrencyInfo>>(emptyList())
    val cryptoCurrencyInfoListLiveData: LiveData<List<CryptoCurrencyInfo>> get() = _cryptoCurrencyInfoListLiveData

    private val _connectionStatusLiveData = MutableLiveData(ConnectionStatus.LOADING)
    val connectionStatusLiveData: LiveData<ConnectionStatus> get() = _connectionStatusLiveData


    fun loadData(currency: Currency) {
        viewModelScope.launch {
            loadCryptoCurrencyInfoListUseCase(currency.id)
                .onSuccess {
                    _cryptoCurrencyInfoListLiveData.postValue(it)
                    _connectionStatusLiveData.postValue(ConnectionStatus.SUCCESS)
                }.onFailure {
                    _connectionStatusLiveData.postValue(ConnectionStatus.FAILURE)
                }
        }
    }

    enum class Currency(val id: String) {
        USD("usd"), EUR("eur")
    }

}