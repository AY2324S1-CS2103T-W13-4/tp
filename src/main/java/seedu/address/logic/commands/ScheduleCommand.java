package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import java.util.stream.Stream;

import java.util.List;

/**
 * ScheduleCommand represents a command to schedule a new appointment.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "Schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedules a new appointment. "
            + "Parameters: "
            + PREFIX_DATE_TIME + "DATETIME "
            + PREFIX_STUDENT + "STUDENT\n"
            + "Example: "
            + COMMAND_WORD + " "
            + PREFIX_DATE_TIME + "2023-12-31 16:30 "
            + PREFIX_STUDENT + "John Doe";

    public static final String MESSAGE_SUCCESS = "New Appointment scheduled: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This appointment already exists in the schedule.";
    public static final String MESSAGE_APPOINTMENT_CLASH = "This appointment clashes with an existing appointment.";

    private final Appointment toAdd;

    public ScheduleCommand(Appointment appointment) {
        requireNonNull(appointment);
        toAdd = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check for clashes with existing appointments
        if (isAppointmentClashing(model.getAppointments(), toAdd)) {
            throw new CommandException(MESSAGE_APPOINTMENT_CLASH);
        }

        // Check for duplicate appointments (if needed)
        if (model.hasAppointment(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.addAppointment(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    private boolean isAppointmentClashing(List<Appointment> appointments, Appointment newAppointment) {
        for (Appointment existingAppointment : appointments) {
            if (existingAppointment.getDateTime().equals(newAppointment.getDateTime())) {
                return true;
            }
        }
        return false;
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
