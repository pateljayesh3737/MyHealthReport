package com.nsh.core.domain.parser

import com.nsh.model.DiagnosticReportDetails
import com.nsh.model.ObservationDetails
import jakarta.inject.Inject
import org.hl7.fhir.r4.model.Bundle
import org.hl7.fhir.r4.model.DiagnosticReport
import org.hl7.fhir.r4.model.Observation

/**
 * Factory implementation of the FHIRModelFactory interface for creating models related to
 * diagnostic reports and observations.
 *
 * This class provides methods to transform FHIR Observation and DiagnosticReport resources
 * into structured data models (ObservationDetails and DiagnosticReportDetails) that can
 * be used within the application. It simplifies the handling of raw FHIR data by extracting
 * and processing the relevant details such as observation values, measurement units,
 * range information, performer names, and diagnostic report metadata.
 *
 * Use this class to create structured models from FHIR resources when working with
 * diagnostic reports and their associated observations.
 */
class DiagnosticReportModelFactory @Inject constructor() : FHIRModelFactory {

    override fun createObservationDetails(observation: Observation, bundle: Bundle): ObservationDetails? {
        val observationName = observation.code.text
            ?: observation.code.coding.firstOrNull()?.display
            ?: "Unknown Observation"

        val value = observation.valueQuantity?.value?.toPlainString()
        val unit = observation.valueQuantity?.unit

        val referenceRangeComponent = observation.referenceRange?.firstOrNull()
        val low = referenceRangeComponent?.low?.value?.toFloat()
        val high = referenceRangeComponent?.high?.value?.toFloat()

        return ObservationDetails(
            observationName = observationName,
            value = value,
            unit = unit,
            low = low,
            high = high,
            codeDisplay = observation.code.coding.firstOrNull()?.display,
            performer = observation.performer.joinToString { it.display ?: "" },
        )
    }

    override fun createDiagnosticReportDetails(
        diagnosticReport: DiagnosticReport,
        observations: List<ObservationDetails>
    ): DiagnosticReportDetails {
        val reportName = diagnosticReport.code.text
            ?: diagnosticReport.code.coding.firstOrNull()?.display
            ?: "Unknown Report Name"

        val testPerformers = diagnosticReport.performer.mapNotNull { it.display }

        val effectiveDateTime = diagnosticReport.effective?.dateTimeValue()?.toHumanDisplay()

        return DiagnosticReportDetails(
            reportName = reportName,
            testPerformers = testPerformers,
            effectiveDateTime = effectiveDateTime,
            observations = observations
        )
    }
}