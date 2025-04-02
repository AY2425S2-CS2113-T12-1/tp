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

<b> How the architecture components interact with each other</b><br>

The <i>Sequence Diagram</i> below shows how the components interact with each other for the scenario where the user issues the command `register John Pork/High Fever/5 Jan 2025 1730/Cheese allergy`

![Sequence Diagram](./pictures/RegisterPatientSequenceDiagram.png)

Each of the main components are separated into functional packages with concrete classes that handle specific responsibilities.

For example, the Manager component contains a TaskManager.java class that parses the input and delegates execution to the respective functions. <br>

In the context of this example:

| Package  | Key Classes     | Responsibilities                                                      |
|----------|-----------------|-----------------------------------------------------------------------|
| main     | MediNote        | Receives raw user input and initialises the command flow              |
| manager  | TaskManager     | Parses inputs and delegates execution to the respective command class |
| commands | RegisterPatient | Contains bulk of code logic                                           |
| storage  | SaveData        | Persists data to text files                                           |


## Product scope
### Target user profile

The target users are hospital management staff.
MediNote provides a way to compile the list of patients and which patients the doctors are assigned to, and has features to help edit and keep track of changes in the hospital.


### Value proposition

MediNote provides a way to easily track and edit patient and doctor assignments in the hospital.
MediNote aims to improve the management capacity and efficiency of hospitals.

## User Stories

| Version | As a ...                  | I want to ...                                    | So that I can ...                                                 |
|---------|---------------------------|--------------------------------------------------|-------------------------------------------------------------------|
| v1.0    | Hospital receptionist     | View medical history of patients                 | Inform the doctor about their past conditions                     |
| v1.0    | New hospital receptionist | View the list of commands available              | Easily navigate data                                              |
| v1.0    | Hospital receptionist     | Be able to put in patient and doctor information | Start tracking new patient progress                               |
| v1.0    | Hospital receptionist     | Update patient and doctor information            | Fix any mistakes and update records                               |
| v1.0    | Hospital receptionist     | Delete patient or doctor records                 | Maintain accuracy and cleanliness of data                         |
| v2.0    | Doctor                    | View patient's information                       | So that I know how to treat them                                  |
| v2.0    | Doctor                    | Update doctor availability                       | Inform the next patient for treatment                             |
| v2.0    | Doctor                    | See patient symptoms                             | Provide good medication quickly                                   |
| v2.0    | Hospital receptionist     | View the status of patients                      | Check whether they have been discharged                           |
| v2.0    | Hospital management       | View the doctors that were visited the most      | Reward them with a break or a pay raise                           |
| v2.0    | Hospital management       | View the type of most frequently visited doctors | Hire more doctors of that specialisation for increased efficiency |

## Non-Functional Requirements

1. Should work on any <i>mainstream</i> OS as long as it has Java `17` or above installed.
2. Should be able to hold up to 1000 persons without a noticeable sluggishness in performance for typical usage.
3. A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to key in most of the records faster using commands than using the mouse.

{More to be added}

## Glossary

* <b>Mainstream OS</b>: Windows, Linux, Unix, macOS

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
