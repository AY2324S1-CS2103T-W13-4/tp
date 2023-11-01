package seedu.address.logic.parser;

import static seedu.address.logic.Messages.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.stream.Stream;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.ScheduleCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ModelManager;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Date;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Time;
import seedu.address.model.appointment.exceptions.InvalidStartEndTimeException;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;

/**
 * Parses input arguments and creates a new ScheduleCommand object.
 */
public class ScheduleCommandParser implements Parser<ScheduleCommand> {

    private final ObservableList<Student> uniqueStudentList;
    private final ModelManager model;

    public ScheduleCommandParser() {
        this.model = ModelManager.getInstance();
        uniqueStudentList = model.getFilteredStudentList();
    }

    /**
     * Parses the given {@code String} of arguments in the context of the ScheduleCommand
     * and returns a ScheduleCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ScheduleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NAME, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_NAME,
                PREFIX_DESCRIPTION) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ScheduleCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DATE, PREFIX_START_TIME, PREFIX_END_TIME,
                PREFIX_DESCRIPTION);

        Name studentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Date date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
        Time startTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_START_TIME).get());
        Time endTime = ParserUtil.parseTime(argMultimap.getValue(PREFIX_END_TIME).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        Appointment appointment;

        if (uniqueStudentList == null || !findStudentByName(studentName)) {
            throw new ParseException(MESSAGE_INVALID_STUDENT);
        }

        try {
            appointment = new Appointment(date, startTime, endTime, studentName, description);
        } catch (InvalidStartEndTimeException e) {
            throw new ParseException(MESSAGE_INVALID_START_END_TIME);
        }

        return new ScheduleCommand(appointment);
    }

    /**
     * Returns true if a student with the given name exists in the list.
     *
     * @param name The name of the student to search for.
     * @return True if a student with the given name exists, false otherwise.
     */
    public boolean findStudentByName(Name name) {
        // Assuming your UniqueStudentList contains a list of Student objects
        return uniqueStudentList.stream().anyMatch(student -> student.getName().equals(name));
    }

    /**
     * Returns true if none of the prefixes contain empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
