# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

### Overall Architecture

![Architecture Diagram](./pictures/Architecture.png)  
This Architecture Diagram represents the high-level design of MediNote.  

### Main Components of Architecture

Main consists of the _MediNote_ class which is in charge of startup and shutting down.  
- At launch, if a save file exists, it will load all patient and doctor information into MediNote.  

In an overview, most work is done by these components:
- **Main**: Reads user input.
- **Storage**: Loads and writes information as MediNote is running.
- **Manager**: Handles overall patient, doctor information and command calls.  
- **Commands**: Executes commands.
- **Ui**: Prints to user *(Currently only help command)*.

## Product scope
### Target user profile

The target users are hospital management staff.
MediNote provides a way to compile the list of patients and which patients the doctors are assigned to, and has features to help edit and keep track of changes in the hospital.


### Value proposition

MediNote provides a way to easily track and edit patient and doctor assignments in the hospital.
MediNote aims to improve the management capacity and efficiency of hospitals.

## User Stories

| Version | As a ...                  | I want to ...                                    | So that I can ...                             |
|---------|---------------------------|--------------------------------------------------|-----------------------------------------------|
| v1.0    | Hospital receptionist     | View medical history of patients                 | Inform the doctor about their past conditions |
| v1.0    | New hospital receptionist | View the list of commands available              | Easily navigate data                          |
| v1.0    | Hospital receptionist     | Be able to put in patient and doctor information | Start tracking new patient progress           |
| v1.0    | Hospital receptionist     | Update patient and doctor information            | Fix any mistakes and update records           |
| v1.0    | Hospital receptionist     | Delete patient or doctor records                 | Maintain accuracy and cleanliness of data     |
| v2.0    | Doctor                    | View patient's information                       | So that I know how to treat them              |
| v2.0    | Doctor                    | Update doctor availability                       | Inform the next patient for treatment         |
| v2.0    | Doctor                    | See patient symptoms                             | Provide good medication quickly               |
| v2.0    | Hospital receptionist     | View the status of patients                      | Check whether they have been discharged       |

## Non-Functional Requirements

{Give non-functional requirements}

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
