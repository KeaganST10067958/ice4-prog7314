package com.fake.quotes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fake.quotes.data.QuoteRepository
import com.fake.quotes.data.model.CatFact
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiState(
    val loading: Boolean = false,
    val fact: CatFact? = null,
    val error: String? = null
)

class QuoteViewModel : ViewModel() {
    private val repo = QuoteRepository()

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    fun loadRandom() {
        _state.value = UiState(loading = true)
        viewModelScope.launch {
            try {
                val f = repo.random()
                _state.value = UiState(fact = f)
            } catch (e: Exception) {
                _state.value = UiState(error = e.message ?: "Unknown error")
            }
        }
    }
}
