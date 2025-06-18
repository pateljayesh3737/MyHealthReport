package com.nsh.core.domain.di

import com.nsh.core.domain.parser.DiagnosticReportModelFactory
import com.nsh.core.domain.parser.FHIRModelFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ParserModule {

    @Binds
    @Singleton
    abstract fun bindFHIRModelFactory(
        impl: DiagnosticReportModelFactory
    ): FHIRModelFactory
}