# User Guide: MediNote

## Introduction

MediNote is a desktop app for managing hospital information such as doctor and patient records, optimised for use 
via a Command Line Interface (CLI).

## Quick Start

{Give steps to get started quickly}

1. Ensure that you have Java 17 or above installed.
1. Down the latest version of `Duke` from [here](http://link.to/duke).

## Features 

- [Register a new patient: `register`](#registering-a-new-patient-register)
- [Add a new doctor: `oncall`](#adding-a-new-doctor-oncall)
- [View patient information: `patient NAME`](#view-patient-information-patient-name)


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


## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

{Give a 'cheat sheet' of commands here}

* Register patient `register NAME/SYMPTOMS/TIMESTAMP/MEDICAL HISTORY`
* Add doctor `oncall NAME/SPECIALISATION/PATIENT NAME`
* View patient information `patient NAME`
