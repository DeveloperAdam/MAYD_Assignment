package com.adam.mayd_assignment.hilt

import android.content.Context
import com.adam.mayd_assignment.utils.MAYD_ApplicationClass
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context) = context as MAYD_ApplicationClass
}