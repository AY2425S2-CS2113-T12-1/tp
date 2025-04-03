# User Guide: MediNote

## Introduction

MediNote is a desktop app for managing hospital information such as doctor and patient records, optimised for use
via a Command Line Interface (CLI).

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `MediNote` from [here](http://link.to/duke).

## Features


- [Show a list of commands: `help`](...)
- [Register a new patient: `register`](#registering-a-new-patient-register)
- [Add a new doctor: `oncall`](#adding-a-new-doctor-oncall)
- [View patient information: `patient <NAME>`](#view-patient-information-patient-name)
- [Discharge a patient: `discharge patient <NAME>`](#discharge-a-patient-discharge-patient-name)
- [Delete a doctor: `delete doctor <NAME>`](#delete-a-doctor-delete-doctor-name)
- [Updates Patient or Doctor in question: `update`](#update-details-about-patient-or-doctor-update)
- [Display popular doctor or specialisation: `popular`](#popular)
- [List all patients: `list patient`](#list-all-patients-list-patient)
- [List all doctors: `list doctor`](#list-all-doctors-list-doctor)
- [View specific patient attribute across all patients: `list patient <ATTRIBUTE>`](#list-patient-attributes-list-patient-attribute)
- [View specific patient attribute individually: `view patient <ATTRIBUTE>`](#view-patient-attributes-view-patient-attribute)
- [View specific doctor attribute individually: `view doctor <ATTRIBUTE>`](#view-doctor-attributes-view-doctor-attribute)
- [Display Hospital Summary Stats: `stats`](#hospital-summary-statistics-dashboard-stats)

### Printing out List of Commands for Reference: `help`
Prints out all Command Words and Instructions on how to use them.<br>

Format: `help`

### Show a list of commands: `help`
Shows a list of commands for navigating around the database.
Provides an explanation on how different commands work to bring out 
or add the desired information from or into the database.

Format: `help`

### Registering a new patient: `register`
Registers a new patient with necessary details:<br>

- `<NAME>`
- `<SYMPTOMS>`
- `<TIMESTAMP>`
- `<MEDICAL HISTORY>`

Format: `register <NAME> / <SYMPTOMS> / <TIMESTAMP> / <MEDICAL HISTORY>`

Example of usage:

`register John Pork/High Fever/5 Jan 2025 1730/Allergic to cheese`

`register Tim Cheese/Constipation/5:23pm 7 Feb 2026/NA`

### Adding a new doctor: `oncall`
Adds a new on-call doctor with necessary details:<br>

- `<NAME>`
- `<SPECIALISATION>`
- `<PATIENT NAME>` (the patient they are currently treating, if any)

Format: `oncall <NAME> / <SPECIALISATION> / <PATIENT NAME>`

Example of usage:

`oncall Simon Claw/Cardiologist/Mr Lim`

### View patient information: `patient <NAME>`
Displays specific patient information such as:<br>

- Name
- Symptoms
- Timestamp
- Medical history
- Doctor assigned (if any)

Format: `patient <NAME>`

Example of usage:

`patient John Pork`

### View doctor information: `doctor <NAME>`
Displays specific doctor information such as:<br>

- Specialisation
- Availability
- Patients under the doctor (if any)

Format: `doctor <NAME>`

### Discharge a patient: `discharge patient <NAME>`
Deletes patient and their corresponding information from the database.

Format: `discharge patient <NAME>`

Example of usage:

`discharge patient Lebron James`

### Delete a doctor: `delete doctor <NAME>`
Deletes doctor and their corresponding information from the database.

Format: `delete doctor <NAME>`

Example of usage:

`delete doctor Michael Jordan`

### Update details about Patient or Doctor: `update`
Updates the details about a particular Patient or Doctor in question.

Format: `update <patient / doctor> <NAME> <Details to be updated according to registration format>`

### Popular

#### Display most popular doctor type: `popular doctor type`
Displays the specialisation with most patient visits. Includes patients currently being treated.

Format & Example usage: `popular doctor type`

#### Display most visited doctor: `popular visited doctor`
Displays the doctor(s) with most patients treated. Includes patients currently being treated.

Format & Example usage: `popular visited doctor`

### List all patients: `list patient`
Displays all patients along with their attributes.

Format: `list patient`

### List all doctors: `list doctor`
Displays all doctors with their full information.

Format: `list doctor`

### List patient attributes: `list patient <ATTRIBUTE>`
Displays a specific attribute for all patients.<br>

Available `<ATTRIBUTE>` values:
- `name`
- `symptoms`
- `timestamp`
- `history`
- `treatment`
- `doctor`

Format: `list patient <ATTRIBUTE>`

Example:
- `list patient symptoms`
- `list patient timestamp`

### View patient attributes: `view patient <ATTRIBUTE>`
Displays each patient's specific attribute one by one with formatting.<br>

Available `<ATTRIBUTE>` values:
- `name`
- `symptoms`
- `timestamp`
- `history`
- `treatment`
- `doctor`


Format: `view patient <ATTRIBUTE>`

Example:
- `view patient treatment`

### View doctor attributes: `view doctor <ATTRIBUTE>`
Displays each doctor's specific attribute one by one with formatting.<br>

Available `<ATTRIBUTE>` values:
- `name`
- `specialisation`
- `availability`
- `treating`
- `numtreated`

Format: `view doctor <ATTRIBUTE>`

Example:
- `view doctor specialisation`

### Hospital Summary Statistics Dashboard: `stats`
Prints an overview of hospital metrics:

- Total number of patients
- Number of patients currently being treated
- Total number of doctors
- Most active doctor (by patient count)
- Average patients per doctor

Format: `stats`

### To Exit: `exit`
Exits from the interface

Format: `exit`

---

## Command Summary

* `help` — Show command reference table
* `register <NAME> / <SYMPTOMS> / <TIMESTAMP> / <MEDICAL HISTORY>` — Register new patient
* `oncall <NAME> / <SPECIALISATION> / <PATIENT NAME>` — Register new doctor
* `patient <NAME>` — View full patient info
* `doctor <NAME>` — View full doctor info
* `list patient` — List all patients with full details
* `list doctor` — List all doctors
* `list patient <ATTRIBUTE>` — List specific patient attribute across all
* `view patient <ATTRIBUTE>` — View each patient's attribute individually
* `view doctor <ATTRIBUTE>` — View each doctor's attribute individually
* `update patient <NAME> <TREATMENT STATUS> <DOCTOR ASSIGNED>` — Update a patient’s info
* `update doctor <NAME> <AVAILABILITY> <PATIENT NAME>` — Update a doctor’s info
* `discharge patient <NAME>` — Delete patient from system
* `delete doctor <NAME>` — Delete doctor from system
* `popular doctor type` — Show most common doctor specialisation
* `popular visited doctor` — Show most visited doctor
* `stats` — Show hospital-wide metrics
* `exit` — Exit the program
