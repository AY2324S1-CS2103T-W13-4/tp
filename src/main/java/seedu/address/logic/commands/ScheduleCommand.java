package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * ScheduleCommand represents a command to schedule a new appointment.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";
    public static final int DAYS_IN_YEAR = 365;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedules a new appointment. "
            + "Parameters: "
            + PREFIX_NAME + "STUDENT "
            + PREFIX_DATE + "DATE "
            + PREFIX_START_TIME + "STARTTIME "
            + PREFIX_END_TIME + "ENDTIME "
            + PREFIX_DESCRIPTION + "DESCRIPTION\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_DATE + "2023-12-31 "
            + PREFIX_START_TIME + "16:30 "
            + PREFIX_END_TIME + "17:30 "
            + PREFIX_DESCRIPTION + "First Session";

    public static final String MESSAGE_SUCCESS = "New appointment scheduled: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists";
    public static final String MESSAGE_OVERLAPPING_APPOINTMENTS = "This appointment overlaps "
                                                                  + "with an existing appointment";

    public static final String MESSAGE_NO_STUDENT_FOR_APPOINTMENT = "No such student exists for this appointment";
    public static final String MESSAGE_DATE_IN_THE_PAST = "Appointment date and time should be in the future";
    public static final String MESSAGE_DATE_EXCEED_ONE_YEAR = "Appointment can only be scheduled within a year";
    public static final String MESSAGE_DATE_DOES_NOT_EXIST = "Date does not exist. Enter a valid date.";

    private final Appointment toAdd;

    /**
     * Creates a new ScheduleCommand to schedule the specified appointment.
     *
     * @param appointment The appointment to be scheduled. Must not be null.
     */
    public ScheduleCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        if (model.hasOverlapsWithAppointments(toAdd)) {
            throw new CommandException(MESSAGE_OVERLAPPING_APPOINTMENTS);
        }

        if (model.hasNoStudentForAppointment(toAdd)) {
            throw new CommandException(MESSAGE_NO_STUDENT_FOR_APPOINTMENT);
        }

        LocalDate appointmentDate = toAdd.getDate().getLocalDate();
        LocalDate todayDate = LocalDate.now();
        LocalTime appointmentStartTime = toAdd.getStartTime().getLocalTime();
        LocalTime timeNow = LocalTime.now();
        long daysDifference = ChronoUnit.DAYS.between(todayDate, appointmentDate);

        if (appointmentDate.isBefore(todayDate)
                || (todayDate.isEqual(appointmentDate) && appointmentStartTime.isBefore(timeNow))) {
            throw new CommandException(MESSAGE_DATE_IN_THE_PAST);
        }

        if (daysDifference > 365) {
            throw new CommandException(MESSAGE_DATE_EXCEED_ONE_YEAR);
        }

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCommand)) {
            return false;
        }

        ScheduleCommand otherScheduleCommand = (ScheduleCommand) other;
        return toAdd.equals(otherScheduleCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
