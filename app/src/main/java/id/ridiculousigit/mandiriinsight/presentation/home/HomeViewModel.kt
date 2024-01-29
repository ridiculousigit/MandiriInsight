package id.ridiculousigit.mandiriinsight.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ridiculousigit.mandiriinsight.common.UIState
import id.ridiculousigit.mandiriinsight.data.remote.model.ArticlesItem
import id.ridiculousigit.mandiriinsight.data.remote.model.NewsModel
import id.ridiculousigit.mandiriinsight.domain.NewsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {
    private var _getSearchNews = MutableStateFlow<PagingData<ArticlesItem>>(PagingData.empty())

    val getHeadline: Flow<UIState<List<ArticlesItem>>> = newsUseCase.executeGetHeadline()
    val getAllNews: Flow<UIState<List<ArticlesItem>>> = newsUseCase.executeGetAllNews()
    val getSearchNews: StateFlow<PagingData<ArticlesItem>> = _getSearchNews
    val getLoadMore: Flow<PagingData<ArticlesItem>> = newsUseCase.executeGetLoadMore().cachedIn(viewModelScope)

    fun getSearchNews(query: String?) {
        viewModelScope.launch {
            newsUseCase.executeGetSearchNews(query).cachedIn(this).collect {
                _getSearchNews.value = it
            }
        }
    }
}