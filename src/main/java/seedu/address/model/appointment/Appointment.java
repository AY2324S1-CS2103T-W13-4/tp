package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

/**
 * Represents an appointment with a date and time, a student, and a description.
 */
public class Appointment {

    private final DateTime dateTime;
    private final Name student;
    private final Description description;

    /**
     * Constructs an Appointment object with the specified date and time, student, and description.
     *
     * @param dateTime    The date and time of the appointment.
     * @param student     The student associated with the appointment.
     * @param description A description of the appointment.
     */
    public Appointment(DateTime dateTime, Name student, Description description) {
        requireAllNonNull(dateTime, student, description);
        this.dateTime = dateTime;
        this.student = student;
        this.description = description;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getDate() {
        return dateTime.getDate();
    }

    public String getTime() {
        return dateTime.getTime();
    }

    public String getName() {
        return student.fullName;
    }


    public Description getDescription() {
        return description;
    }

    /**
     * Returns true if both appointments have the same fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Appointment)) {
            return false;
        }

        Appointment otherAppointment = (Appointment) other;
        return student.equals(otherAppointment.student)
                && description.equals(otherAppointment.description)
                && dateTime.equals(otherAppointment.dateTime);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(student, description, dateTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("student", student)
                .add("description", description)
                .add("dateTime", dateTime)
                .toString();
    }
}
