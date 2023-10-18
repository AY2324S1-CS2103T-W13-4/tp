package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.DateTime;
import seedu.address.model.appointment.Description;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.model.person.UniquePersonList;

import java.util.stream.Stream;

/**
 * Parses input arguments and creates a new ScheduleCommand object.
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {
    private final UniquePersonList uniquePersonList;

    public ScheduleCommandParser(UniquePersonList uniquePersonList) {
        this.uniquePersonList = uniquePersonList;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns a ScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                CliSyntax.PREFIX_DATE_TIME, CliSyntax.PREFIX_STUDENT);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_DATE_TIME,
                CliSyntax.PREFIX_STUDENT) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_DATE_TIME, CliSyntax.PREFIX_STUDENT);

        // Parse the appointment details from the input
        try {
            DateTime dateTime = new DateTime(argMultimap.getValue(CliSyntax.PREFIX_DATE_TIME).get());
            Description description = new Description(argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION).get());
            Name studentName = new Name(argMultimap.getValue(CliSyntax.PREFIX_STUDENT).get());
            Person student = uniquePersonList.findPersonByName(studentName);

            if (student == null) {
                throw new ParseException("Student not found.");
            }

            Appointment appointment = new Appointment(dateTime, student, description);

            return new ScheduleCommand(appointment);
        } catch (Exception e) {
            throw new ParseException("Invalid input format. "
                    + "Please use the correct format for scheduling appointments.");
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
