package seedu.address.logic.parser;

import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Person;
import seedu.address.logic.parser.CliSyntax;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_DESCRIPTION, CliSyntax.PREFIX_DATE_TIME, CliSyntax.PREFIX_STUDENT);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DESCRIPTION, CliSyntax.PREFIX_DATE_TIME, CliSyntax.PREFIX_STUDENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(Messages.MESSAGE_INVALID_COMMAND_FORMAT);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_DESCRIPTION, CliSyntax.PREFIX_DATE_TIME, CliSyntax.PREFIX_STUDENT);

        try {
            String description = argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).orElse(""); // Use an empty string if not provided
            String dateTime = argMultimap.getValue(CliSyntax.PREFIX_DATE_TIME).get();
            String student = argMultimap.getValue(CliSyntax.PREFIX_STUDENT).get();

            // Additional parsing and validation logic can be added here as needed
            Description descriptionObj = new Description(description);
            DateTime dateTimeObj = new DateTime(dateTime);
            Person person = new Person(student);

            Appointment appointment = new Appointment(dateTimeObj, person, descriptionObj);

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
