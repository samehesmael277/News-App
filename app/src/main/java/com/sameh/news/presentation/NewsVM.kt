package com.sameh.news.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sameh.news.domain.repo.INewsRepo
import com.sameh.news.domain.util.Resource
import com.sameh.news.utils.toLogD
import com.sameh.news.utils.toLogE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsVM @Inject constructor(
    private val repo: INewsRepo
) : ViewModel() {

    var state by mutableStateOf(NewsState())
        private set

    fun getNewsFromApi(country: String, apiKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            state = state.copy(isLoading = true)

            when (val result = repo.fetchDataFromApiAndCache(country, apiKey)) {
                is Resource.Success -> {
                    "getNewsFromApi Success: ${result.data}".toLogD()
                    state = state.copy(
                        newsData = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    "getNewsFromApi Error: ${result.message}".toLogE()
                    getLocalNews()
                }
            }
        }
    }

    private fun getLocalNews() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repo.getLocalData()) {
                is Resource.Success -> {
                    "getLocalNews Success: ${result.data}".toLogD()
                    state = state.copy(
                        newsData = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    "getLocalNews Error: ${result.message}".toLogE()
                    state = state.copy(
                        newsData = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}