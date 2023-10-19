package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.ViewCommandParser.APPOINTMENT_CATEGORY;
import static seedu.address.logic.parser.ViewCommandParser.STUDENT_CATEGORY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Lists all appointments or students in WellNus to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View which data you want to see\n"
            + "Parameters: data category (must be 'students' or 'appointments') "
            + "g/ [DATA_CATEGORY]\n"
            + "Example: " + COMMAND_WORD + " g/appointments ";

    public static final String MESSAGE_SUCCESS_APPOINTMENT = "Listed all appointments";

    public static final String MESSAGE_SUCCESS_STUDENT = "Listed all students";

    public static final String MESSAGE_ARGUMENTS = "Data chosen: %1$s";

    private final String category;

    public ViewCommand(String category) {
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (this.category.equals(STUDENT_CATEGORY)) {
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_SUCCESS_STUDENT);
        } else if (this.category.equals(APPOINTMENT_CATEGORY)) {
            model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
            return new CommandResult(MESSAGE_SUCCESS_APPOINTMENT);
        } else {
            throw new CommandException(String.format(MESSAGE_ARGUMENTS, category));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ViewCommand)) {
            return false;
        }

        ViewCommand e = (ViewCommand) other;
        return category.equals(e.category);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("category", category)
                .toString();
    }
}
