package com.mutkuensert.gymtools.feature.bodymeasurements.di

import com.mutkuensert.gymtools.feature.bodymeasurements.navigation.BodyMeasurementsNavigationBuilder
import com.mutkuensert.gymtools.feature.bodymeasurements.navigation.BodyMeasurementsNavigatorImpl
import com.mutkuensert.gymtools.navigation.FeatureNavigationBuilder
import com.mutkuensert.gymtools.navigation.navigators.BodyMeasurementsNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet

@Module
@InstallIn(SingletonComponent::class)
interface BodyMeasurementsModule {

    @Binds
    @IntoSet
    fun bindNavGraphBuilder(builder: BodyMeasurementsNavigationBuilder): FeatureNavigationBuilder

    @Binds
    fun bindNavigator(navigator: BodyMeasurementsNavigatorImpl): BodyMeasurementsNavigator
}
