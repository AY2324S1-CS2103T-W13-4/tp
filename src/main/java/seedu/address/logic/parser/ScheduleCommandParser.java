package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;

/**
 * Parses input arguments and creates a new ScheduleCommand object.
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns a ScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE_TIME, PREFIX_STUDENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE_TIME, PREFIX_STUDENT) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_DATE_TIME, PREFIX_STUDENT);

        // Parse the appointment details from the input
        try {
            String dateTime = argMultimap.getValue(PREFIX_DATE_TIME).get();
            String student = argMultimap.getValue(PREFIX_STUDENT).get();
            // Additional parsing and validation logic can be added here as needed

            Appointment appointment = new Appointment(dateTime, student);

            return new ScheduleCommand(appointment);
        } catch (Exception e) {
            throw new ParseException("Invalid input format. Please use the correct format for scheduling appointments.");
        }
    }

    /**
     * Returns true if none of the prefixes contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
