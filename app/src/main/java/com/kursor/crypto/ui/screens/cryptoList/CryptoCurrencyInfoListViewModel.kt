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

    private val _selectedCurrencyLiveData = MutableLiveData<Currency>()
    val selectedCurrencyLiveData: LiveData<Currency> get() = _selectedCurrencyLiveData

    private val _isRefreshingLiveData = MutableLiveData<Boolean>()
    val isRefreshingLiveData: LiveData<Boolean> get() = _isRefreshingLiveData

    fun loadData(currency: Currency) {
        _selectedCurrencyLiveData.value = currency
        _connectionStatusLiveData.value = ConnectionStatus.LOADING
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

    fun refresh() {
        _isRefreshingLiveData.value = true
        viewModelScope.launch {
            loadCryptoCurrencyInfoListUseCase(
                _selectedCurrencyLiveData.value?.id ?: return@launch
            ).onSuccess {
                _isRefreshingLiveData.postValue(false)
                _cryptoCurrencyInfoListLiveData.postValue(it)
            }
        }
    }

    enum class Currency(val id: String, val symbol: Char) {
        USD(id = "usd", symbol = '$'),
        EUR(id = "eur", symbol = '€')
    }

}