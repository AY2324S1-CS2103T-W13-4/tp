package seedu.address.model.risklevel.exceptions;


/**
 * This class encapsulates the event when the number of risk level tags exceed the
 * specified maximum allowed number of tags.
 */
public class ExceedMaxRiskLevelSizeException extends ArrayIndexOutOfBoundsException {

    public ExceedMaxRiskLevelSizeException(int maxSize) {
        super("number of tags cannot exceed " + maxSize + "!");
    }
}
