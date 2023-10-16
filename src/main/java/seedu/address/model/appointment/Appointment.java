package seedu.address.model.appointment;

import seedu.address.model.person.Person;

/**
 * Represents an appointment with a date and time, a student, and a description.
 */
public class Appointment {
    private DateTime dateTime;
    private Person student;
    private Description description;

    /**
     * Constructs an Appointment object with the specified date and time, student, and description.
     *
     * @param dateTime    The date and time of the appointment.
     * @param student     The student associated with the appointment.
     * @param description A description of the appointment.
     */
    public Appointment(DateTime dateTime, Person student, Description description) {
        this.dateTime = dateTime;
        this.student = student;
        this.description = description;
    }

    /**
     * Retrieves the date and time of the appointment.
     *
     * @return The date and time of the appointment.
     */
    public DateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the date and time of the appointment.
     *
     * @param dateTime The new date and time for the appointment.
     */
    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Retrieves the student associated with the appointment.
     *
     * @return The student associated with the appointment.
     */
    public Person getStudent() {
        return student;
    }

    /**
     * Retrieves the description associated with the appointment.
     *
     * @return The description associated with the appointment.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Sets the student associated with the appointment.
     *
     * @param student The new student for the appointment.
     */
    public void setStudent(Person student) {
        this.student = student;
    }

    /**
     * Sets the description associated with the appointment.
     *
     * @param description The new description for the appointment.
     */
    public void setDescription(Description description) {
        this.description = description;
    }

    /**
     * Checks if this appointment is the same as another appointment. Two appointments are considered the same if they have the same description.
     *
     * @param otherAppointment The appointment to compare with.
     * @return {@code true} if the appointments are the same, {@code false} otherwise.
     */
    public boolean isSameAppointment(Appointment otherAppointment) {
        if (otherAppointment == this) {
            return true;
        }

        return otherAppointment != null
                && otherAppointment.getDescription().equals(getDescription());
    }
}
