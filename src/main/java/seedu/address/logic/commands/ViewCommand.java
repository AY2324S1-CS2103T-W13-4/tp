package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;


/**
 * Lists all persons in the address book to the user.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View which data you want to see\n"
            + "Parameters: data category (must be 'students' or 'appointments') "
            + "g/ [DATA_CATEGORY]\n"
            + "Example: " + COMMAND_WORD + " g/appointments ";

    public static final String MESSAGE_SUCCESS = "Listed all appointments";
    public static final String MESSAGE_ARGUMENTS = "Data chosen: %1$s";

    private final String category;

    public ViewCommand(String category) {
        this.category = category;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENTS);
        return new CommandResult(MESSAGE_SUCCESS);
        // throw new CommandException(String.format(MESSAGE_ARGUMENTS, category));
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
}
