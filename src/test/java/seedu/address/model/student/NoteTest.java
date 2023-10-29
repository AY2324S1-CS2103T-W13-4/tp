package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Note(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidNote = "ijdoasfsjfjsfisfjdijfjfdijfdpsjifpjfdiasfdjfdjfpdjfidsajidsjipfsjifdsjcjp"
                + "dmaspacmdspcmdmcpsdmcdppsdpsdadspoadjaspdasopksapascjaspcjfsapjcspjdasjcaspjdsjapcjsapsajcpsajfas"
                + "ijciasjfpasjfaciasjdajadasdasaasdasd";
        assertThrows(IllegalArgumentException.class, () -> new Note(invalidNote));
    }

    @Test
    public void isValidNote() {

        String twoHundredCharNote = "ijdoasfsjfjsfisfjdijfjfdijfdpsjifpjfdiasfdjfdjfpdjfidsajidsjipfsjifdsjcjpdmaspa"
                + "cmdspcmdmcpsdmcdppsdpsdadspoadjaspdasopksapascjaspcjfsapjcspjdasjcaspjdsjapcjsapsajcpsajfasijcia"
                + "sjfpasjfaciasjdajadasdasa";
        String moreThanTwoHundredCharNote = "ijdoasfsjfjsfisfjdijfjfdijfdpsjifpjfdiasfdjfdjfpdjfidsajidsjipfsjifdsjcjp"
                + "dmaspacmdspcmdmcpsdmcdppsdpsdadspoadjaspdasopksapascjaspcjfsapjcspjdasjcaspjdsjapcjsapsajcpsajfas"
                + "ijciasjfpasjfaciasjdajadasdasaasdasd";

        // null note
        assertThrows(NullPointerException.class, () -> Note.isValidNote(null));

        // invalid notes
        assertFalse(Note.isValidNote(moreThanTwoHundredCharNote)); // more than 200 characters

        // valid note numbers
        assertTrue(Note.isValidNote("")); // empty string
        assertTrue(Note.isValidNote("Likes dogs."));
        assertTrue(Note.isValidNote("a")); // 1 character
        assertTrue(Note.isValidNote(twoHundredCharNote)); // exactly 200 characters
    }

    @Test
    public void equals() {
        Note note = new Note("Hello");

        // same object -> returns true
        assertTrue(note.equals(note));

        // same values -> returns true
        Note noteCopy = new Note(note.value);
        assertTrue(note.equals(noteCopy));

        // different types -> returns false
        assertFalse(note.equals(1));

        // null -> returns false
        assertFalse(note.equals(null));

        // different note -> returns false
        Note differentNote = new Note("Bye");
        assertFalse(note.equals(differentNote));
    }
}
