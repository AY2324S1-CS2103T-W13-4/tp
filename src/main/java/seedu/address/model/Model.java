package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.AppointmentList;
import seedu.address.model.person.Person;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Person> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Person person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Person target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Person person);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same
     * as another existing person in the address book.
     */
    void setPerson(Person target, Person editedPerson);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Person> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Person> predicate);

    // Methods related to appointments
    /**
     * Returns true if an appointment with the same identity as the provided
     * appointment exists in the appointment list.
     *
     * @param appointment The appointment to check for.
     * @return True if the appointment exists, false otherwise.
     */
    boolean hasAppointment(Appointment appointment);

    /**
     * Deletes a given appointment from the appointment list.
     * The appointment must exist in the list.
     *
     * @param target The appointment to be deleted.
     */
    void deleteAppointment(Appointment target);

    /**
     * Adds a given appointment to the appointment list.
     * The appointment must not already exist in the list.
     *
     * @param appointment The appointment to be added.
     */
    void addAppointment(Appointment appointment);

    /**
     * Replaces an appointment in the appointment list with an edited version.
     * The target appointment must exist in the list, and the edited appointment's
     * identity must not be the same as another existing appointment in the list.
     *
     * @param target          The appointment to be replaced.
     * @param editedAppointment The edited appointment to replace the old one.
     */
    void setAppointment(Appointment target, Appointment editedAppointment);

    AppointmentList getAppointments();
}
