package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RISK_LEVEL;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.risklevel.RiskLevel;
import seedu.address.model.student.Student;

/**
 * Edits the details of an existing student in the address book.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tags the student identified "
            + "by the index number used in the displayed student list to a risk level: high/medium/low. "
            + "Existing risk level will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_RISK_LEVEL + "RISK LEVEL] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_RISK_LEVEL + "high";

    public static final String MESSAGE_TAG_STUDENT_SUCCESS = "Tagged Student: %1$s";
    private final Index index;
    private final Set<RiskLevel> riskLevel;

    /**
     * @param index of the student in the filtered student list to edit
     * @param riskLevel details to edit the student with
     */
    public TagCommand(Index index, Set<RiskLevel> riskLevel) {
        requireNonNull(index);
        requireNonNull(riskLevel);

        this.index = index;
        this.riskLevel = riskLevel;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getPhone(),
                studentToEdit.getAddress(), riskLevel, studentToEdit.getNote());

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_TAG_STUDENT_SUCCESS, Messages.format(editedStudent)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        TagCommand otherTagCommand = (TagCommand) other;
        return index.equals(otherTagCommand.index)
                && riskLevel.equals(otherTagCommand.riskLevel);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("riskLevel", riskLevel)
                .toString();
    }
}
