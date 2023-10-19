package seedu.address.model.risklevel;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Set;

import seedu.address.model.util.LimitedHashSet;

/**
 * Represents a RiskLevel in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidRiskLevel(String)}
 */
public class RiskLevel {

    public static final String MESSAGE_CONSTRAINTS = "Risk level should be one of the following three: high/medium/low";
    private static final Set<String> ALLOWED_VALUES = new LimitedHashSet<>(3);

    static {
        ALLOWED_VALUES.add("high");
        ALLOWED_VALUES.add("medium");
        ALLOWED_VALUES.add("low");
    }
    public final String riskLevel;

    /**
     * Constructs a {@code RiskLevel}.
     *
     * @param riskLevel A valid risk level.
     */
    public RiskLevel(String riskLevel) {
        requireNonNull(riskLevel);
        checkArgument(isValidRiskLevel(riskLevel), MESSAGE_CONSTRAINTS);
        this.riskLevel = riskLevel;
    }

    /**
     * Returns true if a given string is a valid tag name.
     */
    public static boolean isValidRiskLevel(String test) {
        requireNonNull(test);
        return ALLOWED_VALUES.contains(test);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RiskLevel)) {
            return false;
        }

        RiskLevel otherTag = (RiskLevel) other;
        return riskLevel.equals(otherTag.riskLevel);
    }

    @Override
    public int hashCode() {
        return riskLevel.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + riskLevel + ']';
    }

}
