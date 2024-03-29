package id.ridiculousigit.mandiriinsight.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import id.ridiculousigit.mandiriinsight.view.home.HomeAllNewsAdapter
import id.ridiculousigit.mandiriinsight.view.home.HomeSearchAdapter
import id.ridiculousigit.mandiriinsight.view.home.HomeHeadlineAdapter
import id.ridiculousigit.mandiriinsight.view.load_more.LoadMoreAdapter

@Module
@InstallIn(ActivityComponent::class)
object AdapterModule {
    @Provides
    fun provideHeadline(): HomeHeadlineAdapter {
        return HomeHeadlineAdapter()
    }

    @Provides
    fun provideSearch(): HomeSearchAdapter {
        return HomeSearchAdapter()
    }

    @Provides
    fun provideAllNews(): HomeAllNewsAdapter {
        return HomeAllNewsAdapter()
    }

    @Provides
    fun provideLoadMoer(): LoadMoreAdapter {
        return LoadMoreAdapter()
    }
}