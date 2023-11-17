package com.investpro.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.investpro.data.RepositoryAnalyticImpl
import com.investpro.data.RepositoryServerImpl
import com.investpro.data.ServiceImpl
import com.investpro.data.SharedKeeperImpl
import com.investpro.domain.RepositoryAnalytic
import com.investpro.domain.RepositoryServer
import com.investpro.domain.Service
import com.investpro.domain.SharedKeeper
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DiModule {

    @Binds
    @Singleton
    abstract fun bindService(service: ServiceImpl): Service

    @Binds
    @Singleton
    abstract fun bindKeeper(sharedKeeper: SharedKeeperImpl): SharedKeeper

    @Binds
    @Singleton
    abstract fun bindRepositoryAnalytic(repository: RepositoryAnalyticImpl): RepositoryAnalytic

    @Binds
    @Singleton
    abstract fun bindRepositoryServer(repository: RepositoryServerImpl): RepositoryServer

}