package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.ALEX_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.ALEX_SECOND_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.BERNICE_APPOINTMENT;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.AppointmentDateMatchesPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAppointmentBook(), new UserPrefs());

    @Test
    public void equals() {
        AppointmentDateMatchesPredicate firstPredicate =
                new AppointmentDateMatchesPredicate(ALEX_APPOINTMENT.getDate().getDate());
        AppointmentDateMatchesPredicate secondPredicate =
                new AppointmentDateMatchesPredicate(ALEX_SECOND_APPOINTMENT.getDate().getDate());

        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstCommand.equals(filterFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void execute_validDate_singleAppointmentFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        AppointmentDateMatchesPredicate predicate = new AppointmentDateMatchesPredicate(ALEX_APPOINTMENT
                .getDate().getDate());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredAppointmentList(), Collections.singletonList(ALEX_APPOINTMENT));
    }

    @Test
    public void execute_validDate_multipleAppointmentsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 2);
        AppointmentDateMatchesPredicate predicate = new AppointmentDateMatchesPredicate(ALEX_SECOND_APPOINTMENT
                .getDate().getDate());
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredAppointmentList(), Arrays.asList(ALEX_SECOND_APPOINTMENT,
                BERNICE_APPOINTMENT));
    }

    @Test
    public void execute_noAppointmentsFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        AppointmentDateMatchesPredicate predicate = new AppointmentDateMatchesPredicate("2023-10-26");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredAppointmentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(expectedModel.getFilteredAppointmentList(), Collections.emptyList());
    }

    @Test
    public void toStringMethod() {
        AppointmentDateMatchesPredicate predicate = new AppointmentDateMatchesPredicate("2023-10-26");
        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }
}