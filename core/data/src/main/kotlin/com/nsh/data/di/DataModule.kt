package com.nsh.data.di

import com.nsh.data.repository.DiagnosticReportRepository
import com.nsh.data.repository.DiagnosticReportRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindDiagnosticReportRepository(
        impl: DiagnosticReportRepositoryImpl
    ): DiagnosticReportRepository
}