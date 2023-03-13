package com.mutkuensert.fitnesspursuit.di

import android.app.Application
import androidx.room.Room
import com.mutkuensert.fitnesspursuit.data.source.BodySizesDao
import com.mutkuensert.fitnesspursuit.data.source.FitnessPursuitDatabase
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
    fun provideAppDatabase(application: Application): FitnessPursuitDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FitnessPursuitDatabase::class.java,
            "fitnesspursuitdatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideBodySizesDao(fitnessPursuitDatabase: FitnessPursuitDatabase): BodySizesDao {
        return fitnessPursuitDatabase.bodySizesDao()
    }
}
