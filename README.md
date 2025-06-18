# Diagnostic Report Viewer

This project is designed to parse and display clinical diagnostic reports adhering to the 
**FHIR (Fast Healthcare Interoperability Resources)** standard. It utilizes the `org.hl7.fhir.r4` library for
parsing and managing FHIR models and provides a clean, readable, and modular code structure. The project is built with
**Kotlin**, **Jetpack Compose**, and follows modern Android development practices.

---

## Table of Contents
- [Setup](#setup)
- [Architecture Overview](#architecture-overview)
- [Project Structure](#project-structure)
- [Usage of FHIR Libraries](#usage-of-fhir-libraries)
- [Best Coding Practices](#best-coding-practices)
- [Design Patterns Used](#design-patterns-used)
- [Clean and Readable Code](#clean-and-readable-code)
- [Screenshots](#screenshots)

---

## Setup

To set up and run the project locally, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd <repository-directory>
   ```

2. **Install Android Studio**:
   Ensure that you have the latest version of [Android Studio](https://developer.android.com/studio) installed.

3. **Open the Project**:
   Open the project in Android Studio.

4. **Sync Dependencies**:
   Gradle should sync automatically. If not, manually sync by navigating to:
   ```
   File -> Sync Project with Gradle Files
   ```

5. **Install Required Plugins**:
   Ensure the following plugins are installed in Android Studio:
   - Kotlin
   - Hilt
   - Compose Compiler

6. **Run the Application**:
   Select a connected device or an emulator and click the **Run** button.

---

## Architecture Overview

The project follows a **MVVM (Model-View-ViewModel)** architecture pattern for clean and maintainable code. The structure includes the following layers:

1. **Model**:
   - Represents the data layer featuring FHIR models, using the `org.hl7.fhir.r4` library to parse and manage FHIR data types.
   - Files like `DiagnosticReportDetails` and `ObservationDetails` model the diagnostic report structure.

2. **View**:
   - Built using **Jetpack Compose** for rendering a modern, declarative UI.
   - All UI components (e.g., `ObservationDetailsDisplay`) are composables that show the details of FHIR's clinical records.

3. **ViewModel**:
   - Provides data to the UI and handles state management using Jetpack libraries (e.g., `lifecycle-viewmodel-compose`).
   - ViewModels ensure the separation of the UI and business logic to follow the **Single Responsibility Principle**.

4. **Data Access**:
   - Uses Retrofit to fetch remote data when applicable.
   - The sample FHIR API returns an R4-compatible JSON response which needs to be parsed for the UI layer.
   - FHIR data is parsed into Kotlin models using `org.hl7.fhir.r4` library and these models then are used by
     the app module to show the report data.

---

## Project Structure

The project uses a modularized structure with each module serving a distinct purpose. This ensures a clean separation
of concerns and promotes reusability, scalability, and maintainability.

### Modules Overview

We have adopted a modularized project structure instead of a monolithic **app** module. This approach, supported by a
dedicated build-logic module, enables us to take full advantage of Gradle's features, such as skipping the compilation
of unchanged source files and only recompiling the modified files within the respective module.

1. **Core Modules**:
   - `:core:domain`: Contains domain-layer code, such as business rules, use cases, and domain-level models.
   - `:core:common`: Includes shared utility classes and extensions used across multiple modules.
   - `:core:data`: Responsible for managing data sources, repositories, and FHIR parsing logic.
   - `:core:network`: Contains networking code, such as the Retrofit API declarations and related configurations.

2. **Feature Modules**:
   - `:feature:home`: Encapsulates UI components and business logic related to the home screen or dashboard.
   - `:feature:display`: Handles the logic and UI for displaying FHIR diagnostic reports and observations.

3. **Build Logic and Convention Plugins**:
   - A centralized `build-logic` directory defines custom Gradle convention plugins, ensuring consistency in dependency management, Kotlin/Java configurations, and other common settings across all modules. This minimizes duplication and simplifies project setup.

   - Key convention plugins include:
      - **Android Application Plugin**: Configures app-level modules.
      - **Android Library Plugin**: Provides configurations for reusable library modules (e.g., `:core:*`).
      - **Kotlin Plugins**: Ensures correct Kotlin setup with features like coroutines and serialization.

   - Gradle modules use well-defined dependencies declared in `libs.versions.toml`, ensuring consistent versioning and management of third-party libraries.

---

## Usage of FHIR Libraries

The `org.hl7.fhir.r4` library is integrated into the project to:

1. **Parse and Work with FHIR Models**:
   - It facilitates mapping `json` or `xml` FHIR resources into Kotlin objects (e.g., `DiagnosticReport`, `Observation`).
   - Example usage:
     ```kotlin
     val parser = FhirContext.forR4().newJsonParser()
     val diagnosticReport = parser.parseResource(DiagnosticReport::class.java, jsonString)
     ```

2. **Validate FHIR Resources**:
   - Ensures that incoming clinical data conforms to FHIR standards using built-in validators.

3. **Manage FHIR Resources**:
   - Supports CRUD operations (Create, Read, Update, Delete) on FHIR objects when integrated with a FHIR backend.

---

## Best Coding Practices

This project adheres to the following coding practices:
1. **Kotlin Language Best Practices**:
   - **Null Safety**: Critical properties like `low`, `high`, and `value` are nullable by design and properly handled
     using Kotlin's null-safety features to avoid crashes.
   - **Data Classes**: Plain data representation is done using Kotlin `data class` for immutability and ease of use.

2. **Jetpack Compose Development Standards**:
   - Declarative UI development separates UI from business logic.
   - Used `@Composable` annotations properly and adhered to `Modifier` chaining for layout control.

3. **Dependency Injection (DI)**:
   - Integrated **Hilt** for DI, improving testability and modularization.

4. **Coding Standards**:
   - Followed established Kotlin coding styles, including meaningful variable and function names, proper formatting, and consistent code styling.
   - Error cases are handled gracefully with default placeholders or fallback mechanisms.

5. **Asynchronous Programming**:
   - Leveraged Kotlin Coroutines for background tasks, ensuring non-blocking operations and smooth user experience.

6. **Testing**:
   - Included `JUnit` for unit tests and AndroidX test libraries for UI and instrumentation testing.

---

## Design Patterns Used

1. **MVVM (Model-View-ViewModel)**:
   - A clean separation of concerns makes the app easy to maintain and test.
   - ViewModel exposes only the necessary data to the View, ensuring independence and modularity.

2. **Builder Pattern**:
   - Used internally by the `org.hl7.fhir.r4` library for assembling FHIR models.

3. **Factory Pattern**:
   - Used for creating instances of complex FHIR models, such as `ObservationDetails` and `DiagnosticReportDetails`.
   - Helps in encapsulating the logic for generating instances of `FHIRModelFactory`, allowing flexibility in extending or altering the creation process without affecting other parts of the system.
   - Specifically, implemented in the `DiagnosticReportModelFactory` class to construct detailed clinical components like observations, reference ranges, and diagnostic report summaries in a structured manner.

4. **Repository Pattern**:
   - All data operations (e.g., fetching reports) are abstracted into a Repository, promoting a clean separation of the logic layer.

---

## Clean and Readable Code

1. **Segregation of Responsibilities**:
   - All files are modularized based on their functionality:
      - **UI Components** (e.g., `ReferenceRangeBar`)
      - **Business Logic** resides in ViewModels.
      - **Models** strictly adhere to the FHIR standard.

2. **Readable and Maintainable**:
   - Functions are kept short with descriptive names.
   - Hardcoding is avoided; constants are used where relevant.

3. **Reusable Components**:
   - Created reusable composables for UI components like `ReferenceRangeBar` that can adapt based on input properties.

4. **Linting and Formatting**:
   - The project adheres to standard linting rules to ensure consistent design and style.

---

## Screenshots
Screenshots can be found in the [screenshots](screenshots) folder.

## Contributions

Feel free to contribute to this project by creating Pull Requests (PRs). Please ensure that your changes follow the project's style and architecture.

---

## License

This project is distributed under the [Apache License 2.0](LICENSE).