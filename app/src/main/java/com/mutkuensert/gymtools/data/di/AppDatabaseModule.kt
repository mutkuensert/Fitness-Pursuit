package com.mutkuensert.gymtools.data.di

import android.app.Application
import androidx.room.Room
import com.mutkuensert.gymtools.data.source.AppDatabase
import com.mutkuensert.gymtools.data.source.BodyMeasurementsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "appdatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideBodyMeasurementsDao(appDatabase: AppDatabase): BodyMeasurementsDao {
        return appDatabase.bodyMeasurementsDao()
    }
}
