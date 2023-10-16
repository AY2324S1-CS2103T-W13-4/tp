package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> new Description(null));

        // invalid descriptions
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(
                "2jT#L8p!@o9QYdG*cZr$uAqXtW%vI3hN6fE5bJ1mKzV4nSsD7iU0lFyRwC2jT#L8p!@o9QYdG*cZr$uAqXtW%vI3hN6fE5bJ1mKz1")
        ); // 101 characters

        // valid descriptions
        assertTrue(Description.isValidDescription("a")); // exactly 1 character
        assertTrue(Description.isValidDescription("test description"));
        assertTrue(Description.isValidDescription("this is a very very long description")); // long descriptions
        assertTrue(Description.isValidDescription(
                "2jT#L8p!@o9QYdG*cZr$uAqXtW%vI3hN6fE5bJ1mKzV4nSsD7iU0lFyRwC2jT#L8p!@o9QYdG*cZr$uAqXtW%vI3hN6fE5bJ1mKz")
        ); // exactly 100 characters
    }

    @Test
    public void equals() {
        Description description = new Description("test");

        // same values -> returns true
        assertTrue(description.equals(new Description("test")));

        // same object -> returns true
        assertTrue(description.equals(description));

        // null -> returns false
        assertFalse(description.equals(null));

        // different types -> returns false
        assertFalse(description.equals(5.0f));

        // different values -> returns false
        assertFalse(description.equals(new Description("test2")));
    }

}
