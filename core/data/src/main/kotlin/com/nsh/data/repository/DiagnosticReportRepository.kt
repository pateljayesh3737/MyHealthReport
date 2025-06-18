package com.nsh.data.repository

import kotlinx.coroutines.flow.Flow

/**
 * Represents a repository interface for managing and retrieving diagnostic reports.
 *
 * This interface defines methods responsible for fetching raw diagnostic report data
 * as a stream (flow) of JSON strings. Implementations of this repository can interact
 * with different data sources (e.g., remote APIs or local databases) to provide the
 * raw report data.
 */
interface DiagnosticReportRepository {
    suspend fun getRawDiagnosticReport(): Flow<String>
}