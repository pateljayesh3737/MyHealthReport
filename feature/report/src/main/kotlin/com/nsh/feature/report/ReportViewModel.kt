package com.nsh.feature.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nsh.core.domain.usecase.GetDiagnosticReportUseCase
import com.nsh.model.DiagnosticReportDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class that handles the presentation logic for the diagnostic report feature.
 *
 * This ViewModel interacts with the [GetDiagnosticReportUseCase] to retrieve diagnostic report details
 * and manages the state to be observed by the UI layer.
 *
 * @constructor Injects the dependencies required for this ViewModel.
 * @property getDiagnosticReportUseCase Use-case for fetching diagnostic report details.
 */
@HiltViewModel
class ReportViewModel @Inject constructor(
    private val getDiagnosticReportUseCase: GetDiagnosticReportUseCase
) : ViewModel() {

    private val _reportUiState = MutableStateFlow<ReportUiState>(ReportUiState.Loading)
    val reportUiState: StateFlow<ReportUiState> = _reportUiState

    init {
        fetchReport()
    }

    fun fetchReport() {
        viewModelScope.launch {
            getDiagnosticReportUseCase.fetchDiagnosticReportDetails()
                .onStart {
                    _reportUiState.value = ReportUiState.Loading
                }
                .catch { e ->
                    e.printStackTrace()
                    _reportUiState.value = ReportUiState.Error(e.message ?: "An unknown error occurred")
                }
                .collect { reportDetails ->
                    _reportUiState.value = ReportUiState.Success(reportDetails)
                }
        }
    }
}

/**
 * Represents the UI state for displaying a diagnostic report in the application.
 *
 * Sealed class structure is utilized to manage and handle various states for the UI effectively.
 */
sealed class ReportUiState {
    object Loading : ReportUiState()
    data class Success(val reportDetails: DiagnosticReportDetails?) : ReportUiState()
    data class Error(val message: String) : ReportUiState()
}