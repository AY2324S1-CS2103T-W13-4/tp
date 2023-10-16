package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Represents a list of appointments. The list is responsible for managing the appointments it contains.
 */
public class AppointmentList implements Iterable<Appointment> {

    // Internal list of appointments
    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();

    // Unmodifiable view of the internal list
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks if the appointment list contains a specific appointment.
     *
     * @param toCheck The appointment to check for.
     * @return {@code true} if the appointment is found, {@code false} otherwise.
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameAppointment);
    }

    /**
     * Adds an appointment to the list.
     *
     * @param toAdd The appointment to add.
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    /**
     * Sets an appointment in the list to a new appointment.
     *
     * @param target           The appointment to be replaced.
     * @param editedAppointment The new appointment to replace the old one.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireAllNonNull(target, editedAppointment);

        int index = internalList.indexOf(target);
        internalList.set(index, editedAppointment);
    }

    /**
     * Removes an appointment from the list.
     *
     * @param toRemove The appointment to be removed.
     */
    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        internalList.remove(toRemove);
    }

    /**
     * Sets the entire list of appointments to a new list of appointments.
     *
     * @param replacement The new list of appointments.
     */
    public void setAppointments(AppointmentList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Sets the entire list of appointments to a new list of appointments.
     *
     * @param appointments The new list of appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        requireAllNonNull(appointments);
        internalList.setAll(appointments);
    }

    /**
     * Retrieves an unmodifiable view of the internal list of appointments.
     *
     * @return An unmodifiable observable list of appointments.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Returns an iterator over the appointments in the list.
     *
     * @return An iterator of appointments.
     */
    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    /**
     * Checks if this appointment list is equal to another object.
     *
     * @param other The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AppointmentList)) {
            return false;
        }

        AppointmentList otherAppointmentList = (AppointmentList) other;
        return internalList.equals(otherAppointmentList.internalList);
    }

    /**
     * Returns a hash code value for the appointment list.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns a string representation of the appointment list.
     *
     * @return A string representation of the list of appointments.
     */
    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Checks if the appointments in a list are unique.
     *
     * @param appointments The list of appointments to check.
     * @return {@code true} if all appointments are unique, {@code false} otherwise.
     */
    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).isSameAppointment(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
