package com.mutkuensert.gymtools.feature.base.di

import com.mutkuensert.gymtools.feature.base.navigation.BaseNavigatorImpl
import com.mutkuensert.gymtools.navigation.navigators.BaseNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BaseModule {

    @Binds
    fun bindNavigator(navigator: BaseNavigatorImpl): BaseNavigator
}