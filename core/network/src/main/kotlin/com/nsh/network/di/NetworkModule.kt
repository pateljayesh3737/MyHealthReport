package com.nsh.network.di

import com.nsh.network.service.DiagnosticReportService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDiagnosticReportService(retrofit: Retrofit): DiagnosticReportService {
        return retrofit.create(DiagnosticReportService::class.java)
    }
}

private const val BASE_URL = "https://build.fhir.org/"