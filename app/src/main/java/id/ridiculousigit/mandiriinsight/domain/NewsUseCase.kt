package id.ridiculousigit.mandiriinsight.domain

import androidx.paging.PagingData
import id.ridiculousigit.mandiriinsight.common.UIState
import id.ridiculousigit.mandiriinsight.data.remote.model.ArticlesItem
import kotlinx.coroutines.flow.Flow

class NewsUseCase(
    private val newsRepository: NewsRepository
) {
    fun executeGetHeadline(): Flow<UIState<List<ArticlesItem>>> = newsRepository.getHeadline()
    fun executeGetAllNews(): Flow<UIState<List<ArticlesItem>>> = newsRepository.getAllNews()
    fun executeGetLoadMore(): Flow<PagingData<ArticlesItem>> = newsRepository.getLoadMoreNews()
    fun executeGetSearchNews(query: String?): Flow<PagingData<ArticlesItem>> = newsRepository.getSearchNews(query)
}