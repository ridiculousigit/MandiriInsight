package id.ridiculousigit.mandiriinsight.presentation.load_more

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ridiculousigit.mandiriinsight.data.remote.model.ArticlesItem
import id.ridiculousigit.mandiriinsight.domain.NewsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class LoadMoreViewModel @Inject constructor(
    newsUseCase: NewsUseCase
) : ViewModel() {
    val getLoadMore: Flow<PagingData<ArticlesItem>> = newsUseCase.executeGetLoadMore().cachedIn(viewModelScope)
}