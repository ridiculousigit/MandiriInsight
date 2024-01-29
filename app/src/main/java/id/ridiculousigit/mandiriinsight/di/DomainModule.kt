package id.ridiculousigit.mandiriinsight.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.ridiculousigit.mandiriinsight.data.api.NewsApi
import id.ridiculousigit.mandiriinsight.domain.NewsRepository
import id.ridiculousigit.mandiriinsight.domain.NewsRepositoryImpl
import id.ridiculousigit.mandiriinsight.domain.NewsUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepositoryImpl(newsApi)
    }

    @Singleton
    @Provides
    fun provideNewsUseCase(newsRepository: NewsRepository): NewsUseCase {
        return NewsUseCase(newsRepository)
    }
}