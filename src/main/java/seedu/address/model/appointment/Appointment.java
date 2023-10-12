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
     * Sets the student associated with the appointment.
     *
     * @param student The new student for the appointment.
     */
    public void setStudent(Person student) {
        this.student = student;
    }
}
