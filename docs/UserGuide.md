---
layout: page
title: User Guide
---

WellNUS is a **desktop application used by NUS Counsellors to manage and schedule appointments with their student clients**
It is optimised for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `wellnus.jar` from [here](https://github.com/AY2324S1-CS2103T-W13-4/tp).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `exit` : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
e.g. in `add student n/NAME c/CONTACT_NUMBER`, `NAME` and `CONTACT_NUMBER` are parameters 
which can be used as `add student n/John Doe c/98172645`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a student: `add`

Adds a student with his/her relevant details.

Format: `add student n/<NAME> c/<CONTACT_NUMBER> a/<HOME_ADDRESS>`

**Parameters**:
1. Name
   - Alphabetical characters only
2. Contact Number
   - Numerical characters only
3. Home Address
   - No restrictions

Examples:
* `add student n/John c/81349705 a/Yishun Street 56 Blk 21 #05-07`
* `add student n/Sally c/94149785 a/Woodlands Street 11 Blk 888 #08-08`

### View all students

Shows a list of all students.

Format: `view students`

### Delete a student

Deletes an existing student.

Format: `delete <INDEX>`

**Parameters**:
1. Appointment Index
    - Numerical characters only

Examples:
* `delete appointment 2`

### Tag student to risk level //TODO

### Add an appointment

Adds a new appointment for a student.

Format: `add appointment d/<DATETIME_OF_APPOINMENT> n/<NAME_OF_STUDENT>`

**Parameters**:
1. Date/Time of appointment
   - Must be in one of the following formats: `yyyy-MM-dd HH:mm`, or `HH:mm`. If in `HH:mm` the appointment date is set on the date of creation
2. Name
    - Alphabetical characters only

Examples:
- `add appointment d/2023-12-31 16:30 n/Jon`
- `add appointment d/18:30 n/Kiat`

### View all appointments 

Shows a list of all appointments.

Format: `view appointments`

### Delete an appointment 

Deletes an existing appointment.

Format: `delete appointment <INDEX>`

**Parameters**:
1. Appointment Index
    - Numerical characters only

Examples:
* `delete appointment 2`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Tracking TODOS `[coming in v1.3]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

--------------------------------------------------------------------------------------------------------------------

## Command summary //TODO: update your respective things

| Action                        | Format, Examples                                                                                                                        |
|-------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| **Add a student**             | `add student n/<NAME> p/<PHONE_NUMBER> a/<HOME_ADDRESS> …​` <br> e.g., `add student n/John c/81349705 a/Yishun Street 56 Blk 21 #05-07` |
| **View all students**         | `view students`                                                                                                                         |
| **Delete a student**          | `delete student <INDEX>`<br> e.g., `delete student 3`                                                                                   |
| **Tag student to risk level** | `TODO`                                                                                                                                  |
| **Add an appointment**        | `add appointment d/<DATETIME_OF_APPOINMENT> n/<NAME_OF_STUDENT>`<br> e.g., `add appointment d/2023-12-31 16:30 n/Jon`                   |
| **View appointments**         | `view appointments`                                                                                                                     |
| **Delete an appointment**     | `delete appointment <INDEX>`<br> e.g., `delete appointment 3`                                                                           |
| **Help**                      | `help`                                                                                                                                  |
| **Exit**                      | `exit`                                                                                                                                  |
