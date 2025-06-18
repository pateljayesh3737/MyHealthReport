package com.nsh.network

import com.nsh.core.common.network.Dispatcher
import com.nsh.core.common.network.NshDispatchers
import com.nsh.network.service.DiagnosticReportService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DiagnosticReportRemoteDataSource @Inject constructor(
    @Dispatcher(NshDispatchers.Default) private val defaultDispatcher: CoroutineDispatcher,
    private val diagnosticReportService: DiagnosticReportService
) {

    suspend fun fetchDiagnosticReport(): String {
        return withContext(defaultDispatcher) {
            diagnosticReportService.fetchDiagnosticReport()
        }
    }
}