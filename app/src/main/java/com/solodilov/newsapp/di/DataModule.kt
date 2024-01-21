package com.solodilov.newsapp.di

import android.app.Application
import com.solodilov.newsapp.data.datasource.database.NewsDao
import com.solodilov.newsapp.data.datasource.database.NewsDatabase
import com.solodilov.newsapp.data.repository.NewsRepositoryImpl
import com.solodilov.newsapp.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {

	@Singleton
	@Binds
	fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository

	companion object {

		@Singleton
		@Provides
		fun provideNewsDatabase(application: Application): NewsDatabase {
			return NewsDatabase.getInstance(application)
		}

		@Singleton
		@Provides
		fun provideNewsDao(newsDatabase: NewsDatabase): NewsDao {
			return newsDatabase.newsDao()
		}
	}

}