package com.example.interviewtask.di

import android.content.Context
import com.example.interviewtask.database.DataBaseProvider
import com.example.interviewtask.database.MainDataBase
import com.example.interviewtask.database.DataBaseSource
import com.example.interviewtask.data.datastore.DataStore
import com.example.interviewtask.data.datastore.DataStoreImpl
import com.example.interviewtask.network.HttpClientBuilderFactory
import com.example.interviewtask.network.Services
import com.example.interviewtask.domain.repository.MainRepository
import com.example.interviewtask.data.repository.MainRepositoryImpl
import com.example.interviewtask.domain.interactor.DataListUseCase
import com.example.interviewtask.domain.interactor.DataListUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesService(httpClient: HttpClientBuilderFactory): Services = Services.createService(httpClient)

    @Provides
    @Singleton
    fun provideHttpBuilderFactory() : HttpClientBuilderFactory = HttpClientBuilderFactory()

    @Provides
    fun provideMovieDataBaseRoom(@ApplicationContext context: Context) : MainDataBase = DataBaseProvider(context).createDB()

    @Provides
    fun provideMovieDataBaseSource(mainDataBase: MainDataBase) : DataBaseSource = DataBaseSource(mainDataBase)

    @Provides
    fun provideMovieDataStore(services: Services, dataBase: DataBaseSource) : DataStore = DataStoreImpl(services,dataBase)

    @Provides
    fun provideMovieRepository(dataStore: DataStore) : MainRepository = MainRepositoryImpl(dataStore)

    @Provides
    fun provideMovieListUseCase(mainRepository: MainRepository) : DataListUseCase = DataListUseCaseImp(mainRepository)
}