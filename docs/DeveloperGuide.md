---
layout: page
title: Developer Guide
---
* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

* This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](./images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the address book data i.e., all `Student` objects (which are contained in a `UniqueStudentList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<div markdown="span" class="alert alert-info">:information_source: **Note:** An alternative (arguably, a more OOP) model is given below. It has a `Tag` list in the `AddressBook`, which `Student` references. This allows `AddressBook` to only require one `Tag` object per unique tag, instead of each `Student` needing their own `Tag` objects.<br>

<img src="images/BetterModelClassDiagram.png" width="450" />

</div>


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------
## **WellNus Implementation**

This section describes some noteworthy details on how certain features of WellNus are implemented.

### View Feature

This feature is facilitated by the use of the ViewCommand class which extends the Command interface.

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.
![Interactions between LogicManager and ModelManager for a view command](images/ViewSequenceDiagram.png)
<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `ViewCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

View Command handles both the viewing of all students and all appointments. The workflow is shown below:
![ViewCommand Control Flow](images/ViewActivityDiagram.png)
<div markdown="span" class="alert alert-info">:information_source: **Note:** There should be a diamond connecting the 3 separate branches
but due to a limitation of PlantUML, the 3 branches leads to the "end" individually .
</div>
### Student Notes feature

The adding of student notes is facilitated by `NoteCommand`. It extends `Command` and allows the addition of a `Note`
to the student at the index specified by the user.

The sequence diagram below illustrates the interactions within the `Logic` component, 
taking `execute("note 1 note/Likes dogs.")` as an example.

![Interactions inside the Logic component for "note 1 note/Likes dogs." command](images/NoteSequenceDiagram.png)

#### Design considerations:

**Aspect: How a `Note` should be added to a `Student`**

* **Alternative 1 (current choice):** Create a new `NoteCommand` class which handles the addition of a note 
to a `Student`.
    * Pros: User input will be shorter in length and easier to read
    * Cons: More work to implement

* **Alternative 2:** Add `Note` as a field in the `AddCommand`
    * Pros: Easier to implement
    * Cons: User will have to type much longer commands, since `Note` can be up to 200 characters long,
  leads to very lengthy commands

--------------------------------------------------------------------------------------------------------------------
## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

![UndoRedoState0](images/UndoRedoState0.png)

Step 2. The user executes `delete 5` command to delete the 5th student in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

![UndoRedoState1](images/UndoRedoState1.png)

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

![UndoRedoState2](images/UndoRedoState2.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</div>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

![UndoRedoState3](images/UndoRedoState3.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</div>

The following sequence diagram shows how the undo operation works:

![UndoSequenceDiagram](images/UndoSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</div>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<div markdown="span" class="alert alert-info">:information_source: **Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</div>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

![UndoRedoState4](images/UndoRedoState4.png)

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

![UndoRedoState5](images/UndoRedoState5.png)

The following activity diagram summarizes what happens when a user executes a new command:

<img src="images/CommitActivityDiagram.png" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* NUS Student Counsellors
* has a need to manage a significant number of students
* need to manage student details as well as appointments and todos
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps

**Value proposition**: This product is meant to help the counsellors better schedule their appointments with students faster than a typical mouse/GUI driven app. Users will be able to store details like personal information, appointment dates, number of visits, emergency contacts etc.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​    | I want to …​                                 | So that I can…​                                                                      |
|----------|------------|----------------------------------------------|--------------------------------------------------------------------------------------|
| `* * *`  | counsellor | add a student profile                        | keep track of each student's information                                             |
| `* * `   | counsellor | set or edit a student's risk profile         | monitor the risk profile of each student                                             |
| `* * *`  | counsellor | remove a student profile                     | remove student in the event they do not require any further consultation             |
| `* * *`  | counsellor | view a student profile                       | look up a students relevant information, consultation notes etc.                     |
| `* * *`  | counsellor | view all students as a list                  | look up all students that require consultation                                       |
| `* *`    | counsellor | edit a student's profile                     | update student particulars should there be any changes                               |
| `* *`    | counsellor | filter list by types of student              | look up students in particular categories                                            |
| `* *`    | counsellor | find students by name or ID                  | look up particular students                                                          |
| `* *`    | counsellor | schedule an appointment                      | keep track of my appointments in the application                                     |
| `* *`    | counsellor | cancel an appointment                        | remove any appointments that have been cancelled                                     |
| `* *`    | counsellor | view my appointments                         | look through my timetable for the day/week                                           |
| `* *`    | counsellor | edit appointment info                        | plan my schedule accordingly if there are any last-minute changes                    |
| `* *`    | counsellor | sort appointments by date                    | organise my appointments and plan my timetable accordingly                           |
| `*`      | counsellor | block out busy times                         | prevent clashes in scheduling                                                        |
| `*`      | counsellor | prevent double booking                       | prevent clashes in scheduling                                                        |
| `*`      | counsellor | link students to the respective appointments | have easy access to the student profile that can help me prepare for the appointment |
| `*`      | counsellor | add todos items                              | keep track of todos for each student                                                 |
| `*`      | counsellor | view todos items                             | view todos for students at a glance                                                  |
| `*`      | counsellor | link todos items to students                 | have easy access to the contact information for the students                         |
| `*`      | counsellor | sort todos items by dateline                 | organise my appointments and plan my schedule accordingly                            |

### Use cases

(For all use cases below, the **System** is `WellNUS` and the **Actor** is the `counselor`, unless specified otherwise)

**Use case: Add a student #UC01**

**MSS**

1.  User requests to list students
2.  WellNUS shows the list of students
3.  User requests to add a new student to the list
4.  WellNUS adds the student, and shows confirmation message

    Use case ends.

**Extensions**

* 3a. The student name/contact number already exists.
  * 3a1. WellNUS shows an error message. 
    * Use case ends.

* 3b. The given name is invalid (non-alphabetical input) or contact number is invalid (non-numerical input).
  * 3b1. WellNUS shows an error message.
    * Use case ends.


**Use case: View existing students #UC02**

**MSS**

1.  User requests to list students
2.  WellNUS shows the list of students
3.  User can find student index that can be used for other use cases, eg. edit student info

    Use case ends.

**Extensions**

* 2a. The list is empty
  * Use case ends.


**Use case: Delete an existing student #UC03**

**MSS**

1.  User requests to list students
2.  WellNUS shows the list of students
3.  User can find student index
4.  Delete user by specifying the index
5.  Get confirmation of successful delete

    Use case ends.

**Extensions**

* 3a. The student index is invalid.
  * 3a1. WellNUS shows an error message.
    * Use case ends.


**Use case: Tag student to risk level #UC04**

**MSS**

1.  User requests to list students
2.  WellNUS shows the list of students
3.  User can find student index
4.  Tag/change student risk level using the student index
5.  Information gets updated for future reference

    Use case ends.

**Extensions**

* 4a. The student index is invalid.
  * 4a1. WellNUS shows an error message.
    * Use case ends.

* 4b. The risk level is invalid (not high/medium/low)
  * 4b1. WellNUS shows an error message.
    * Use case ends.


**Use case: Schedule an appointment #UC05**

**MSS**

1.  User requests to list appointments
2.  WellNUS shows the list of appointments, along with some basic information like time and student
3.  User requests to schedule a new appointment to the list
4.  WellNUS adds the appointment 
5.  WellNUS shows confirmation message 
    
    Use case ends.

**Extensions**

* 3a. The student index is invalid.
  * 3a1. WellNUS shows an error message.
    * Use case ends.

* 3b. The given time is invalid (wrong time format).
  * 3b1. WellNUS shows an error message.
    * Use case ends.

* 3c. The given time overlaps with an existing appointment.
  * 3c1. WellNUS shows an error message.
    * Use case ends.

**Use case: View existing appointments #UC06**

**MSS**

1.  User chooses to list appointments
2.  WellNUS shows the list of appointments
3.  User can view appointment information such as student involved and time of appointment
    
    Use case ends.

**Extensions**

* 2a. User unable to view appointment information as list is empty

    Use case ends.

**Use case: Cancel an existing appointment #UC07**

**MSS**

1.  User chooses to list appointments
2.  WellNUS shows the list of appointments
3.  User cancels an appointment at chosen index
4.  WellNUS deletes the appointment with index specified by user and display status

    Use case ends.

**Extensions**

* 3a. WellNUS detects an error in the entered index

    Use case resumes from step 1.


**Use case: Tag student to appointment #UC08**

**MSS**

1.  User chooses to list appointments
2.  WellNUS shows the list of appointments
3.  User chooses to list students
4.  WellNUS shows the list of students
5.  User tags student to appointment
6.  WellNUS tags student to appointment and display status
    
    Use case ends.

**Extensions**
* 5a. WellNUS detects an error in either the entered student index or appointment index
    
  Use case resumes from step 1.

**Use case: Add a ToDo #UC09**

**MSS**

1.  User requests to list existing ToDos
2.  WellNUS shows the list of ToDos, along with some basic information like dateline and student
3.  User requests to add a new ToDo to the list
4.  WellNUS adds the ToDo, and shows confirmation message

    Use case ends.

**Extensions**

* 3a. The given student name does not exist.

    * 3a1. WellNUS shows an error message.

    Use case ends.

* 3b. The given student name is invalid (non-alphabetical input) / dateline is invalid (past dateline, invalid format).

    * 3b1. WellNUS shows an error message.

    Use case ends.

**Use case: View existing ToDos #UC10**

**MSS**

1.  User requests to list ToDos
2.  WellNUS shows the list of ToDos
3.  Users can find information like dateline, student, etc.

    Use case ends.

**Extensions**

* 2a. The list is empty.

  Use case ends.

**Use case: Delete an existing ToDo #UC11**

**MSS**

1.  User requests to list ToDos
2.  WellNUS shows the list of ToDos
3.  User can find ToDos index
4.  Delete ToDos by specifying the index
5.  Get confirmation of successful delete

    Use case ends.

**Extensions**

* 3a. The ToDo index is invalid.

    * 3a1. WellNUS shows an error message.

    Use case ends.

**Use case: Tag student to ToDo #UC12**

**MSS**

1.  User requests to list students
2.  WellNUS shows the list of students
3.  User can find student index
4.  Tag/change student to ToDo
5.  Information gets updated for future reference

    Use case ends.


**Extensions**


* 3a. The given index is invalid.

    * 3a1. WellNUS shows an error message.

    Use case ends.

* 3b. The referenced ToDo is invalid.

    * 3a1. WellNUS shows an error message.

    Use case ends.

*{More to be added}*

### Non-Functional Requirements
1.  Cross-Platform Compatibility:
    - Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Scalability and Performance:
    - Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
    - Should support efficient data retrieval and manipulation for the specified contact volume.
3.  Usability and Efficiency:
    - A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should 
    be able to perform the majority of tasks more quickly using _CLI_ commands compared to using a mouse on the _GUI_.
    - The _CLI_ interface should prioritise efficiency by providing clear and concise commands, minimising unnecessary prompts,
    and offering time-saving shortcuts.
4. Updates and Maintenance:
    - Updates should not disrupt the user's workflow or data.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **CLI**: Command Line Interface. Usually the in-built terminal or in the IDE the application is run on.
* **GUI**: Graphical User Interface.
* **Risk Level**: Students can be classified as high, medium, or low-risk level determined by the counsellor.

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

### Schedule an appointment

1. Dealing with clashes
   1. Expected: Warning and appointment not added
2. Multiple High Risk appointments back-to-back
   1. Expected: Warning but can book appointment


