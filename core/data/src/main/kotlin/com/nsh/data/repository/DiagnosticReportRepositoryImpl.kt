package com.nsh.data.repository

import com.nsh.network.DiagnosticReportRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of the DiagnosticReportRepository interface for managing and retrieving
 * diagnostic reports from a remote data source.
 *
 * This class utilizes a remote data source to fetch raw diagnostic report data in JSON format
 * and emits it as a flow. The remote data source handles the actual network request and provides
 * the JSON response.
 *
 * @constructor Creates an instance of DiagnosticReportRepositoryImpl which depends on a
 * DiagnosticReportRemoteDataSource.
 * @param remoteDataSource The remote data source responsible for fetching diagnostic report data.
 */
class DiagnosticReportRepositoryImpl @Inject constructor(
    private val remoteDataSource: DiagnosticReportRemoteDataSource
) : DiagnosticReportRepository {

    override suspend fun getRawDiagnosticReport(): Flow<String> = flow {
        val rawJson = remoteDataSource.fetchDiagnosticReport()
        emit(rawJson)
    }
}