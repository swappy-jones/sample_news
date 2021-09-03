package com.comnet.test

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    public val newsResponse:MutableLiveData<NewsResponse> = MutableLiveData()

    init{
        val retrofitApi = RetrofitFactory.api
        viewModelScope.launch {
            val news  = retrofitApi.getNews()
            CoroutineScope(Dispatchers.Main).launch {
                newsResponse.postValue(news)
            }
        }
    }
}