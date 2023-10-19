package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_APPOINTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CATEGORY_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ViewCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void equals() {
        final ViewCommand standardCommand = new ViewCommand(VALID_CATEGORY_APPOINTMENT);

        // same values -> returns true
        ViewCommand commandWithSameValues = new ViewCommand(VALID_CATEGORY_APPOINTMENT);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different category -> returns false
        assertFalse(standardCommand.equals(new ViewCommand(VALID_CATEGORY_STUDENT)));
    }

    @Test
    public void execute_viewAppointments() {
        assertCommandSuccess(new ViewCommand("appointments"), model,
                ViewCommand.MESSAGE_SUCCESS_APPOINTMENT, expectedModel);
    }

    @Test
    public void execute_viewStudents() {
        assertCommandSuccess(new ViewCommand("students"), model,
                ViewCommand.MESSAGE_SUCCESS_STUDENT, expectedModel);
    }

    @Test
    public void toStringMethod() {
        String category = "appointments";
        ViewCommand viewCommand = new ViewCommand(category);
        String expected = ViewCommand.class.getCanonicalName() + "{category=" + category + "}";
        assertEquals(expected, viewCommand.toString());
    }
}
