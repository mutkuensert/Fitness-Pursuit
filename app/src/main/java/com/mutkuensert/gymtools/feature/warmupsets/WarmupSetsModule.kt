package com.mutkuensert.gymtools.feature.warmupsets

import com.mutkuensert.gymtools.navigation.FeatureNavigationBuilder
import com.mutkuensert.gymtools.navigation.navigators.WarmupSetsNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface WarmupSetsModule {

    @Binds
    @IntoSet
    fun bindNavGraphBuilder(builder: WarmupSetsNavigationBuilder): FeatureNavigationBuilder

    @Binds
    fun bindNavigator(navigator: WarmupSetsNavigatorImpl): WarmupSetsNavigator
}