---
layout: page
title: User Guide
---

WellNUS is a **desktop application used by NUS Counsellors to manage and schedule appointments with their student clients**
It is optimised for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).
If you can type fast, WellNUS can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## 1. Getting started

###  1.1 Accessing the app

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `wellnus.jar` from [here](https://github.com/AY2324S1-CS2103T-W13-4/tp).

3. Copy the file to the folder you want to use as the _home folder_ for WellNUS.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar wellnus.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it.
   Some example commands you can try:
   * `help` : Opens the help window.
   * `exit` : Exits the app.

6. Refer to the [Features](#2-features) below for details of each command.

### 1.2 Prefixes and parameters used in commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME c/CONTACT_NUMBER`, `NAME` and `CONTACT_NUMBER` are parameters
  which can be used as `add n/John Doe c/98172645`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [r/RISK_LEVEL]` can be used as `n/John Doe r/low` or as `n/John Doe`.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

--------------------------------------------------------------------------------------------------------------------

## 2. Features

### 2.1 Utility Commands

#### 2.1.1 Viewing help: `help`

Shows a message with a link to the help page.

![help message](images/helpMessage.png)

Format: `help`

### 2.2 Student Commands

#### 2.2.1 Adding a Student: `add`

Adds a student with their relevant details.

Format: `add n/STUDENT_NAME c/CONTACT_NUMBER a/HOME_ADDRESS [r/RISK_LEVEL]`

**Parameters**:
1. Name
   - Alphabetical characters only
2. Contact Number
   - Numbers only, must be 8 characters long
3. Home Address
   - Maximum of 200 characters
4. Risk Level
   - Must be `high`, `medium`, or `low`

Examples:
* `add n/John c/81349705 a/Yishun Street 56 Blk 21 #05-07`
* `add n/Sally c/94149785 a/Woodlands Street 11 Blk 888 #08-08 r/low`

#### 2.2.2 Deleting a Student: `delete`

Deletes an existing student.

Format: `delete STUDENT_INDEX`

**Parameters**:
1. Student Index
   - Must be an integer starting from 1
   - Must be found in the students list

#### 2.2.3 Adding notes for a Student: `note`

Adds a note to an existing student, overwrites any existing note.

Format: `note STUDENT_INDEX note/NOTE`

**Parameters**:
1. Student Index
   - Must be an integer starting from 1
   - Must be found in the students list
2. Note
   - Maximum of 500 characters

#### 2.2.4 Viewing all Students: `view`

Shows a list of all available students or appointments, depending on specified input.

Format: `view g/CATEGORY`

**Parameters**:
1. Category
    - Must be 'students', 'appointments' or 'all'

Examples:
* `view g/students`

#### 2.2.5 Finding Students by Name: `find`

Find students and their related appointments based on their name. Can choose to find student based on their first name, last name or full name.
If the name does not match entirely, the student will not be shown. Refer to the examples below for a better understanding

Format: `find STUDENT_NAME`

Example Scenario:

Student Name: Roy Lee
* "find Roy" works
* "find Lee" works
* "find Roy Lee" works
* "find Ro" does not work
* "find Le" does not work
* "find Roy L" does not work

#### 2.2.6 Assigning risk level to Student: `tag`

Tags a student to a specific risk level.

Format: `tag STUDENT_INDEX r/RISK_LEVEL`

**Parameters**:
1. Student Index
   - Must be an integer starting from 1
   - Must be found in the students list
2. Risk Level
   - Must be `high`, `medium`, or `low`

Examples:
* `tag 2 r/high`

### 2.3 Appointment Commands

<div markdown="span" class="alert alert-info">:information_source: **Note:**
Appointments will be automatically sorted by Date and Time in ascending order.
</div>

#### 2.3.1 Scheduling an Appointment: `schedule`

Schedules a new appointment for a student.

Format: `schedule n/STUDENT_NAME date/DATE from/START_TIME to/END_TIME d/DESCRIPTION`

**Parameters**:
1. Name
    - Alphabetical characters only
    - Must be the name of a student found in the students list
2. Date
    - Must be in the following format: `yyyy-mm-dd`
3. Start/End Time
    - Must be in the following format: `HH:mm`
4. Description
   - Minimum length of 1 character and maximum of 100 characters

Examples:
- `schedule n/Jon date/2023-12-30 from/16:30 to/17:30 d/monthly check-up`
- `schedule n/Yin Kiat date/2023-01-09 from/07:00 to/10:45 d/first counselling session`

#### 2.3.2 Cancelling an Appointment: `cancel`

Cancels an existing appointment.

Format: `cancel APPOINTMENT_INDEX`

**Parameters**:
1. Appointment Index
   - Must be an integer starting from 1
   - Must be found in the appointments list

Examples:
* `cancel 2`

#### 2.3.3 Viewing all Appointments: `view`

Shows a list of all available students or appointments, depending on specified input.

Format: `view g/CATEGORY`

**Parameters**:
1. Category
   - Must be 'students', 'appointments' or 'all'

Examples:
* `view g/appointments`

#### 2.3.4 Filtering Appointments by Date: `filter`

Filters appointments based on given date. 

Format: `filter DATE`

**Parameters**
1. Date
   - Must be in the following format: `yyyy-mm-dd`

Examples:
* `filter 2023-10-16`

### 2.4 Others

#### 2.4.1 Viewing all Students and Appointments: `view`

Shows a list of all available students or appointments, depending on specified input.

Format: `view g/CATEGORY`

**Parameters**:
1. Category
   - Must be 'students', 'appointments' or 'all'

Examples:
* `view g/all`

#### 2.4.2 Exiting the program: `exit`

Exits the program.

Format: `exit`

#### 2.4.3 Clearing storage: `clear`

Resets the storage, deleting **all** Appointments and Students. 

Format: `clear`

#### 2.4.4 Saving the data

WellNUS data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

#### 2.4.5 Editing the data file

WellNUS data is saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file
<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, WellNUS will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### 2.5 Tracking TODOS `[Coming soon]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## 3. Frequently Asked Questions (FAQ)

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous WellNUS home folder.

--------------------------------------------------------------------------------------------------------------------

## 4. Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## 5. Command summary

| Action                                                                            | Format, Examples                                                                                                                                                |
|-----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Help](#211-viewing-help-help)                                                    | `help`                                                                                                                                                          |
| [Add Student](#221-adding-a-student-add)                                          | `add n/STUDENT_NAME c/CONTACT_NUMBER a/HOME_ADDRESS [r/RISK_LEVEL]` <br> e.g., `add n/John c/81349705 a/Yishun Street 56 Blk 21 #05-07 r/medium`                |
| [Delete Student](#222-deleting-a-student-delete)                                  | `delete STUDENT_INDEX`<br> e.g., `delete 3`                                                                                                                     |
| [Add Student Note](#223-adding-notes-for-a-student-note)                          | `note STUDENT_INDEX note/NOTE` <br> e.g., `note 1 note/Likes dogs`                                                                                              |
| [View Students](#224-viewing-all-students-view)                                   | `view g/students`                                                                                                                                               |
| [Find Students](#225-finding-students-by-name-find)                               | `find STUDENT_NAME` <br> e.g., `find John`                                                                                                                      |
| [Assign Risk Level to Student](#226-assigning-risk-level-to-student-tag)          | `tag STUDENT_INDEX r/RISK_LEVEL`<br> e.g.,`tag 4 r/high`                                                                                                        |
| [Schedule Appointment](#231-scheduling-an-appointment-schedule)                   | `schedule n/STUDENT_NAME date/DATE from/START_TIME to/END_TIME d/DESCRIPTION`<br> e.g., `schedule n/Jon date/2023-12-30 from/16:30 to/17:30 d/monthly check-up` |
| [Cancel Appointment](#232-cancelling-an-appointment-cancel)                       | `cancel APPOINTMENT_INDEX`<br> e.g., `cancel 3`                                                                                                                 |
| [View Appointments](#233-viewing-all-appointments-view)                           | `view g/appointments`                                                                                                                                           |
| [Filter Appointments](#234-filtering-appointments-by-date-filter)                 | `filter DATE` <br> e.g., `filter 2023-10-16`                                                                                                                    |
| [View Appointments and Students](#241-viewing-all-students-and-appointments-view) | `view g/all`                                                                                                                                                    |
| [Exit](#242-exiting-the-program-exit)                                             | `exit`                                                                                                                                                          |
| [Delete all data](#243-clearing-storage-clear)                                    | `clear`                                                                                                                                                         |


