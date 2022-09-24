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

    private val _refreshingLiveData = MutableLiveData(RefreshingState.NOT_REFRESHING)
    val refreshingLiveData: LiveData<RefreshingState> get() = _refreshingLiveData

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
        _refreshingLiveData.value = RefreshingState.REFRESHING
        viewModelScope.launch {
            loadCryptoCurrencyInfoListUseCase(
                _selectedCurrencyLiveData.value?.id ?: return@launch
            ).onSuccess {
                _refreshingLiveData.postValue(RefreshingState.NOT_REFRESHING)
                _cryptoCurrencyInfoListLiveData.postValue(it)
            }.onFailure {
                _refreshingLiveData.postValue(RefreshingState.FAILURE)
            }
        }
    }

    enum class Currency(val id: String, val symbol: Char) {
        USD(id = "usd", symbol = '$'),
        EUR(id = "eur", symbol = '€')
    }

    enum class RefreshingState {
        REFRESHING,
        NOT_REFRESHING,
        FAILURE
    }

}