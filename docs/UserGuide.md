# User Guide: MediNote

## Introduction

MediNote is a desktop app for managing hospital information such as doctor and patient records, optimised for use 
via a Command Line Interface (CLI).

## 💡 Quick Start 💡

1. Ensure that you have **Java 17** or above installed.
2. Down the latest version of `MediNote` from [here](https://github.com/AY2425S2-CS2113-T12-1/tp).
3. Copy the application to the directory you want to use as the **working directory**.
4. In your terminal, move to the directory containing the application and run it with `java -jar tP_V2.jar`.

## Features 

- [Listing all patients: `list patient`](#listing-all-current-patients-list-patient)
- [Listing all doctors: `list doctor`](#listing-all-current-doctors-list-doctor)
- [Viewing specific doctors: `doctor`](#viewing-specified-doctor-doctor)
- [Viewing specific patients: `patient`](#viewing-specified-patient-patient)
- [Register a new patient: `register`](#registering-a-new-patient-register)
- [Add a new doctor: `oncall`](#adding-a-new-doctor-oncall)
- [View patient information: `patient NAME`](#view-patient-information-patient-name)
- [Discharge a patient: `discharge`](#discharge-a-patient-discharge-patient-name)
- [Delete a doctor: `delete`](#delete-a-doctor-delete-doctor-name)
- [Display popular doctor or specialisation: `popular`](#popular)

### Listing all current patients: `list patient`
Lists all patients currently admitted, along with all of their attributes:<br>

Format: `list patient`

Example output of a list with 2 patients:
```
You have 2 patient(s) in hospital
1. Mr. A:
    >Time of Admission: 01 JAN 2025
    >Symptoms: Cough
    >Medical History: NA
    >Treatment Status: NA
    >Doctor Assigned: NA

2. Si En:
    >Time of Admission: Tomorrow
    >Symptoms: Eye Fever
    >Medical History: None
    >Treatment Status: NA
    >Doctor Assigned: NA
```

### Listing all current doctors: `list doctor`
Lists all doctors currently working, along with all of their attributes:<br>

Format: `list doctor`

Example output of a list with 2 doctors:<br>
```
You have 2 doctor(s) in hospital
1. Pengu:
    >Specialization: Eye Fever Doctor
    >Availability: NA
    >Currently treating: NA

2. Timothy Cheese:
    >Specialization: Knee Pain
    >Availability: NA
    >Currently treating: NA
```

### Viewing specified doctor: `doctor`
Views all the attributes of doctors specified in the command:<br>
`doctor` searches for all doctors mentioned by the user.

Format: `doctor <NAME_1>/<NAME_2>/.../<NAME_X>`

Example usage:<br>
`doctor Timothy Cheese/Pengu`

Example output:<br>
```
    Details of specified doctors:
        >Name: Timothy Cheese
            >Specialisation: Knee Pain
            >Availability: NA
            >Currently treating: NA
        >Name: Pengu
            >Specialisation: Eye Fever Doctor
            >Availability: NA
            >Currently treating: NA
```

### Viewing specified patient: `patient`
Views all the attributes of patients specified in the command:<br>
`patient` searches for all patients mentioned by the user.

Format: `patient <NAME_1>/<NAME_2>/.../<NAME_X>`

Example usage:<br>
`patient Mr. A/Si En`

Example output:<br>
```
    Details of specified patients:
        >Name: Mr. A
            >Symptoms: Cough
            >Time Stamp: 01 JAN 2025
            >Medical History: NA
            >Treatment Status: NA
            >Doctor Assigned: NA
        >Name: Si En
            >Symptoms: Eye Fever
            >Time Stamp: Tomorrow
            >Medical History: None
            >Treatment Status: NA
            >Doctor Assigned: NA
```

### Registering a new patient: `register`
Registers a new patient with necessary details:<br>

- NAME
- SYMPTOMS
- TIMESTAMP
- MEDICAL HISTORY

Format: `register NAME/SYMPTOMS/TIMESTAMP/MEDICAL HISTORY`

Example usage: 

`register John Pork/High Fever/5 Jan 2025 1730/Allergic to cheese`

`register Tim Cheese/Constipation/5:23pm 7 Feb 2026/NA`

### Adding a new doctor: `oncall`
Adds a new on-call doctor with necessary details:<br>

- NAME
- SPECIALISATION
- PATIENT NAME

Format: `oncall NAME/SPECIALISATION/PATIENT NAME`

Example usage:

`oncall Simon Claw/Cardiologist/Mr Lim`

### View patient information: `patient NAME`
Displays specific patient information such as:<br>

- NAME
- SYMPTOMS
- TIMESTAMP
- MEDICAL HISTORY
- DOCTORS ASSIGNED (if any)

Format: `patient NAME`

Example usage:

`patient John Pork`

### Discharge a patient: `discharge patient NAME`
Deletes patient and their corresponding information from the database.

Format: `discharge patient NAME`

Example usage:

`discharge patient Lebron James`

### Delete a doctor: `delete doctor NAME`
Deletes doctor and their corresponding information from the database.

Format: `delete doctor NAME`

Example usage:

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

{Give a 'cheat sheet' of commands here}

* Register patient `register NAME/SYMPTOMS/TIMESTAMP/MEDICAL HISTORY`
* Add doctor `oncall NAME/SPECIALISATION/PATIENT NAME`
* View patient information `patient NAME`
