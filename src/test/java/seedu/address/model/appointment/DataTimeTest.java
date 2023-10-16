package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Address;

public class DataTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTime = "";
        assertThrows(IllegalArgumentException.class, () -> new DateTime(invalidDateTime));
    }

    @Test
    public void isValidDateTime() {
        // valid DateTime
        assertTrue(DateTime.isValidDateTime("2023-10-14 15:30:45"));
        assertTrue(DateTime.isValidDateTime("2023-11-14 12:10:55"));
        assertTrue(DateTime.isValidDateTime("2023-10-31 12:10:55"));

        // invalid DateTime
        assertFalse(DateTime.isValidDateTime("2023-10-14")); // only date, no time
        assertFalse(DateTime.isValidDateTime("15:30:45")); // only time, no date
        assertFalse(DateTime.isValidDateTime("2023-10-14 15:30:")); // incomplete
        assertFalse(DateTime.isValidDateTime("2023-10-14 15:301:")); // extra digit
        assertFalse(DateTime.isValidDateTime("2023-10-14 15:AM:")); // contains non-digit
        assertFalse(DateTime.isValidDateTime("2023-10-14 15:30:60")); // 60 seconds
        assertFalse(DateTime.isValidDateTime("2023-10-14 15:60:15")); // 60 minutes
        assertFalse(DateTime.isValidDateTime("2023-13-14 15:60:15")); // month 13
        assertFalse(DateTime.isValidDateTime("2023-10-32 15:60:15")); // day 32
        assertFalse(DateTime.isValidDateTime("1999-10-14 15:60:15")); // year 1999
    }

    @Test
    public void equals() {
        DateTime dateTime = new DateTime("2023-10-14 15:30:45");

        // same values -> returns true
        assertTrue(dateTime.equals(new DateTime("2023-10-14 15:30:45")));

        // same object -> returns true
        assertTrue(dateTime.equals(dateTime));

        // null -> returns false
        assertFalse(dateTime.equals(null));

        // different types -> returns false
        assertFalse(dateTime.equals(5.0f));

        // different values -> returns false
        assertFalse(dateTime.equals(new Address("2023-10-14 15:30:46")));
    }
}
