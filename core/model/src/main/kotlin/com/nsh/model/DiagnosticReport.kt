package com.nsh.model

/**
 * Represents detailed information about a diagnostic report, including its name, the list of test performers,
 * the effective date and time of the report, and associated observations.
 *
 * @property reportName The name of the diagnostic report.
 * @property testPerformers A list of strings representing the individuals or entities that performed the tests.
 * @property effectiveDateTime The date and time when the diagnostic report became effective, or null if not available.
 * @property observations A list of observation details associated with the diagnostic report, where each observation provides
 * information about a specific test or measurement, including its name, value, unit, reference range, and optionally its performer.
 */
data class DiagnosticReportDetails(
    val reportName: String,
    val testPerformers: List<String>,
    val effectiveDateTime: String?,
    val observations: List<ObservationDetails>
)

/**
 * Represents detailed information related to a specific observation, which could be part of a
 * diagnostic report or a medical test. This includes the observation's name, measured value,
 * unit of measurement, reference range, and performer details.
 *
 * @property observationName The name or title of the observation or measurement being represented.
 * Typically describes what is being measured.
 * @property value The measured value of the observation, or null if no value is provided. Can represent
 * data like a test result or measurement reading.
 * @property unit The unit of measurement for the observation's value, such as "mg/dL" or "%". This is null
 * if the unit is not specified or not applicable.
 * @property low The lower bound of the reference range for the observation's value, or null if no
 * lower bound is defined. Represented as a floating-point number.
 * @property high The upper bound of the reference range for the observation's value, or null if no
 * upper bound is defined. Represented as a floating-point number.
 * @property codeDisplay Optional display text or description based on a standardized code associated
 * with the observation. Could include additional metadata or context about the observation's type.
 * @property performer The name or identifier of the individual or entity who performed or recorded
 * the observation. Null if such information is not provided.
 */
data class ObservationDetails(
    val observationName: String,
    val value: String?,
    val unit: String?,
    val low: Float?,
    val high: Float?,
    val codeDisplay: String? = null,
    val performer: String? = null,
)