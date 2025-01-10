package com.sumin.coroutineflow.crypto_app

import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CryptoViewModel : ViewModel() {

    private val repository = CryptoRepository



    val state: Flow<State> = repository.getCurrencyList()
        .filter { it.isNotEmpty() }
        .map { State.Content(currencyList = it) as State }
        .onStart { emit(State.Loading) }

    fun refreshList() {
        viewModelScope.launch {
            repository.refreshList()
        }
    }

}

