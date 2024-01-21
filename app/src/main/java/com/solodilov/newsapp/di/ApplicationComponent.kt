package com.solodilov.newsapp.di

import android.app.Application
import com.solodilov.newsapp.presentation.news.NewsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
	DataModule::class,
	NetworkModule::class,
	ViewModelModule::class,
])
interface ApplicationComponent {

	fun inject(fragment: NewsFragment)


	@Component.Factory
	interface Factory {
		fun create(
			@BindsInstance application: Application,
		): ApplicationComponent
	}
}