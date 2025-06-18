package com.nsh.core.domain.parser

import com.nsh.model.DiagnosticReportDetails
import com.nsh.model.ObservationDetails
import org.hl7.fhir.r4.model.Bundle
import org.hl7.fhir.r4.model.DiagnosticReport
import org.hl7.fhir.r4.model.Observation

/**
 * Interface for creating FHIR model representations of observations and diagnostic reports.
 *
 * This factory is responsible for transforming raw FHIR resources, such as Observations and
 * Diagnostic Reports, into data models that can be used within the application. It facilitates
 * the processing of FHIR resources into structured models with relevant attributes such as
 * observation details or diagnostic report details.
 */
interface FHIRModelFactory {

    /**
     * Creates an ObservationDetails model from a FHIR Observation resource.
     *
     * @param observation The FHIR Observation resource to transform
     * @param bundle The FHIR Bundle containing related resources
     * @return ObservationDetails model or null if the observation cannot be processed
     */
    fun createObservationDetails(observation: Observation, bundle: Bundle): ObservationDetails?

    /**
     * Creates a DiagnosticReportDetails model from a FHIR DiagnosticReport resource.
     *
     * @param diagnosticReport The FHIR DiagnosticReport resource to transform
     * @param observations List of processed ObservationDetails associated with this report
     * @return DiagnosticReportDetails model containing the processed report information
     */
    fun createDiagnosticReportDetails(
        diagnosticReport: DiagnosticReport,
        observations: List<ObservationDetails>
    ): DiagnosticReportDetails
}