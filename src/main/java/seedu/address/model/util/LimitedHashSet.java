package seedu.address.model.util;

import java.util.HashSet;

import seedu.address.model.risklevel.exceptions.ExceedMaxRiskLevelSizeException;

/**
 * This class inherits from a HashSet but specifies a maximum size.
 *
 * @param <T> The type of the element in the LimitedHashSet.
 */
public class LimitedHashSet<T> extends HashSet<T> {

    private int maxSize;

    public LimitedHashSet(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public boolean add(T element) {
        if (size() >= maxSize) {
            throw new ExceedMaxRiskLevelSizeException(maxSize);
        }

        return super.add(element);
    }
}
