package com.nsh.core.domain.usecase

import com.nsh.core.domain.parser.FHIRModelFactory
import com.nsh.data.repository.DiagnosticReportRepository
import com.nsh.model.DiagnosticReportDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.hl7.fhir.r4.formats.JsonParser
import org.hl7.fhir.r4.model.Bundle
import org.hl7.fhir.r4.model.DiagnosticReport
import org.hl7.fhir.r4.model.Observation
import javax.inject.Inject

/**
 * Use-case for retrieving diagnostic report details by processing raw diagnostic report data.
 *
 * This class provides functionality to fetch a diagnostic report as raw data in JSON format from
 * the repository, parse it, and transform it into a structured [DiagnosticReportDetails] data model.
 * The parsing involves extracting the report name, performer details, effective date, and associated
 * observations. Observations are further processed to include relevant details like value, unit,
 * reference range, and performer information.
 *
 * @constructor Injects the dependencies required for this use case.
 * @param repository The repository that provides the raw diagnostic report data.
 */
class GetDiagnosticReportUseCase @Inject constructor(
    private val repository: DiagnosticReportRepository,
    private val fhirModelFactory: FHIRModelFactory
) {

    suspend fun fetchDiagnosticReportDetails(): Flow<DiagnosticReportDetails?> {
        return repository.getRawDiagnosticReport().map { jsonData ->
            parseDiagnosticReportToModel(jsonData)
        }
    }

    /**
     * Parses raw JSON data representing a FHIR Bundle into a structured `DiagnosticReportDetails` model.
     *
     * This method extracts a `DiagnosticReport` resource from the provided JSON data, processes its
     * observations, and transforms them into a structured format by interacting with the `FHIRModelFactory`.
     *
     * @param jsonData The raw JSON string containing the FHIR Bundle data to be parsed.
     * @return A `DiagnosticReportDetails` model containing the processed diagnostic report information
     *         and its associated observations, or `null` if parsing fails or no diagnostic report is found.
     */
    private fun parseDiagnosticReportToModel(jsonData: String): DiagnosticReportDetails? {
        val parser = JsonParser()
        val bundle: Bundle? = parser.parse(jsonData) as? Bundle
        val diagnosticReport: DiagnosticReport? =
            bundle?.entry?.firstNotNullOfOrNull { it.resource as? DiagnosticReport }

        return diagnosticReport?.let { report ->
            val observations = report.result.mapNotNull { resultRef ->
                val observation = bundle.entry
                    ?.mapNotNull { it.resource as? Observation }
                    ?.find { it.id == resultRef.reference?.substringAfter("Observation/") }

                observation?.let { fhirModelFactory.createObservationDetails(it, bundle) }
            }

            fhirModelFactory.createDiagnosticReportDetails(report, observations)
        }
    }
}