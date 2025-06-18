package com.nsh.network.service

import retrofit2.http.GET

/**
 * Service interface for managing network operations related to diagnostic reports.
 *
 * This interface is used to fetch diagnostic reports from a remote server or API endpoint.
 * It is typically implemented using Retrofit to enable seamless integration with HTTP-based APIs.
 *
 * Methods:
 * - `fetchDiagnosticReport()`: Retrieves a diagnostic report in JSON format from a predefined URL.
 */
interface DiagnosticReportService {

    @GET("diagnosticreport-example.json")
    suspend fun fetchDiagnosticReport(): String
}