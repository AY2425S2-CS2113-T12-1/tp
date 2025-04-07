# raidanus - Project Portfolio Page

---

## 🏥 MediNote - Hospital Management System 🏥

---

## Overview
MediNote is a command-line interface (CLI) hospital management system
designed to streamline the workflow of medical staff by managing patient
and doctor records efficiently.

Built with Java, it caters to healthcare professionals who prefer fast,
keyboard-driven interactions for maintaining critical medical data.

---
## Summary of Contribution

Code Contributed: [Reposense link to RaidaNUS code dashboard](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=RaidaNUS&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21)

### 🚀 Features and Classes Added

- **DoctorUpdater Class**  
  Allows updating a doctor’s availability and patients being treated using flexible input like:  
  `update doctor Dr Lim availability=Busy treating=John Tan`  
  Supports multiple field updates in a single line and ensures changes are persisted using the `SaveData` class.

- **PatientUpdater Class**  
  Supports dynamic patient updates, including status and doctor assignment:  
  `update patient Mr A status=Admitted doctor=Dr Lim`  
  Ensures bidirectional linking: when a doctor is assigned to a patient, the patient's name is added to the doctor’s record as well.

- **ViewDoctorAttributes**  
  Allows filtered inspection of doctor data. Example:  
  `view doctor availability`  
  Outputs a clean summary of doctor availability across the system.

- **ViewPatientAttributes**  
  Enables summary views of patient fields such as symptoms, history, timestamp, etc. Example:  
  `view patient symptoms`

- **OverallStatistics**  
  Generates real-time metrics for the hospital including:
    - Total patients
    - Number of doctors
    - Most active doctor (by patient count)
    - Average patient load per doctor

---

### 🧪 Testing Contributions

- Wrote **JUnit tests** for:
    - `DoctorUpdater`
---

### 📘 Documentation Contributions

- **User Guide Structure and Skeleton**  
   authored the **User Guide skeleton**
- Ensured professional documentation quality and a uniform experience across command documentation.

- **Command Descriptions**  
  Wrote and refined detailed UG entries for:
    - `update patient`
    - `update doctor`
    - `view patient`
    - `view doctor`
    - `stats`

---

### 📊 UML Diagrams and Design

- **Sequence Diagrams Created For:**
    - `PatientUpdater`
    - `DoctorUpdater`
    - `ViewPatientAttributes`
    - `ViewDoctorAttributes`

- **Sequence Diagram Proposals:**
    - `update patient` and `update doctor` flow (with bidirectional updates) "dssws"
    - Attribute extraction flow from the view classes

These diagrams were implemented using PlantUML and demonstrate control flow and class-level responsibilities.
