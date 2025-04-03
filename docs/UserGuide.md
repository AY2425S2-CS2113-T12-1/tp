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
- [View patient information: `patient NAME`](#view-patient-information-patient-name)
- [Discharge a patient: `discharge`](#discharge-a-patient-discharge-patient-name)
- [Delete a doctor: `delete`](#delete-a-doctor-delete-doctor-name)
- [Display popular doctor or specialisation: `popular`](#popular)


### Show a list of commands: `help`
Shows a list of commands for navigating around the database.
Provides an explanation on how different commands work to bring out 
or add the desired information from or into the database.

Format: `help`

### Registering a new patient: `register`
Registers a new patient with necessary details:<br>

- NAME
- SYMPTOMS
- TIMESTAMP
- MEDICAL HISTORY

Format: `register NAME/SYMPTOMS/TIMESTAMP/MEDICAL HISTORY`

Example of usage: 

`register John Pork/High Fever/5 Jan 2025 1730/Allergic to cheese`

`register Tim Cheese/Constipation/5:23pm 7 Feb 2026/NA`

### Adding a new doctor: `oncall`
Adds a new on-call doctor with necessary details:<br>

- NAME
- SPECIALISATION
- PATIENT NAME

Format: `oncall NAME/SPECIALISATION/PATIENT NAME`

Example of usage:

`oncall Simon Claw/Cardiologist/Mr Lim`

### View patient information: `patient NAME`
Displays specific patient information such as:<br>

- NAME
- SYMPTOMS
- TIMESTAMP
- MEDICAL HISTORY
- DOCTORS ASSIGNED (if any)

Format: `patient NAME`

Example of usage:

`patient John Pork`

### Discharge a patient: `discharge patient NAME`
Deletes patient and their corresponding information from the database.

Format: `discharge patient NAME`

Example of usage:

`discharge patient Lebron James`

### Delete a doctor: `delete doctor NAME`
Deletes doctor and their corresponding information from the database.

Format: `delete doctor NAME`

Example of usage:

`delete doctor Michael Jordan`

### Popular

#### Display most popular doctor type: `popular doctor type`
Displays the specialisation with most patient visits. Includes patients currently being treated.

Format & Example usage: popular doctor type

#### Display most visited doctor: `popular visited doctor`
Displays the doctor(s) with most patients treated. Includes patients currently being treated.

Format & Example usage: popular visited doctor


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

| Command            | Description                                                                           |
|--------------------|---------------------------------------------------------------------------------------|
| `help`             | Shows all available commands                                                          |
| `register`         | Add new patient: `NAME / SYMPTOMS / TIMESTAMP / MED_HISTORY`                          |
| `oncall`           | Add doctor: `NAME / SPECIALISATION`                                                   |
| `patient NAME`     | View patient details (case-insensitive)                                               |
| `doctor NAME`      | View doctor details (case-insensitive)                                                |
| `list patient`     | Show all active patients                                                              |
| `list doctor`      | Show all available doctors                                                            |
| `update patient`   | Modify: treatment status or assigned doctor                                           |
| `update doctor`    | Modify: availability or current patient                                               |
| `discharge patient`| Remove patient record                                                                 |
| `delete doctor`    | Remove doctor record                                                                  |
| `clear patients`   | Delete all patient data                                                               |
| `clear doctors`    | Delete all doctor data                                                                |
| `exit`             | Quit the program                                                                      |

**Detailed Formats:**
- `register`: `NAME / SYMPTOMS / TIMESTAMP / MEDICAL_HISTORY`
- `oncall`: `NAME / SPECIALISATION`
- Patient info includes: name, symptoms, timestamp, history, status, doctor
- Doctor info includes: name, specialisation, availability, patients

* Register patient `register NAME/SYMPTOMS/TIMESTAMP/MEDICAL HISTORY`
* Add doctor `oncall NAME/SPECIALISATION/PATIENT NAME`
* View patient information `patient NAME`
