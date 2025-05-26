package com.example.fetch_test_app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetch_test_app.data.Item
import com.example.fetch_test_app.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    private val _items = MutableStateFlow<Map<Int, List<Item>>>(emptyMap())
    val items: StateFlow<Map<Int, List<Item>>> = _items

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            try {
                val raw = RetrofitInstance.api.getItems()
                val cleaned = raw
                    .filter { !it.name.isNullOrBlank() }
                    .sortedWith(compareBy({ it.listId }, { it.name }))
                    .groupBy { it.listId }
                _items.value = cleaned
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}