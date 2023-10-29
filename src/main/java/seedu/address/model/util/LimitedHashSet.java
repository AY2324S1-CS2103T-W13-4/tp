package seedu.address.model.util;

import java.util.Collection;
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
        checkSize(1);
        return super.add(element);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        checkSize(c.size());
        return super.addAll(c);
    }

    /**
     * Checks if the element(s) to be added will exceed the allowed maximum size of the Hashset.
     *
     * @param numElementsToAdd The number of elements that will be added.
     */
    public void checkSize(int numElementsToAdd) {
        if (size() + numElementsToAdd > maxSize) {
            throw new ExceedMaxRiskLevelSizeException(maxSize);
        }
    }
}
