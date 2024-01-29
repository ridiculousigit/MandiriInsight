package id.ridiculousigit.mandiriinsight.domain

import androidx.paging.PagingData
import id.ridiculousigit.mandiriinsight.utils.UIState
import id.ridiculousigit.mandiriinsight.data.model.ArticlesItem
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getHeadline(): Flow<UIState<List<ArticlesItem>>>
    fun getAllNews(): Flow<UIState<List<ArticlesItem>>>
    fun getLoadMoreNews(): Flow<PagingData<ArticlesItem>>
    fun getSearchNews(query: String?): Flow<PagingData<ArticlesItem>>
}