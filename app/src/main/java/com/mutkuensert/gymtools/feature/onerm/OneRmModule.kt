package com.mutkuensert.gymtools.feature.onerm

import com.mutkuensert.gymtools.navigation.FeatureNavigationBuilder
import com.mutkuensert.gymtools.navigation.navigators.OneRmNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface OneRmModule {

    @Binds
    @IntoSet
    fun bindNavGraphBuilder(builder: OneRmNavigationBuilder): FeatureNavigationBuilder

    @Binds
    fun bindNavigator(navigator: OneRmNavigatorImpl): OneRmNavigator
}
