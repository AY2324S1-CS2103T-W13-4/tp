package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.risklevel.RiskLevel;

/**
 * Jackson-friendly version of {@link RiskLevel}.
 */
class JsonAdaptedRiskLevel {

    private final String riskLevel;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code riskLevel}.
     */
    @JsonCreator
    public JsonAdaptedRiskLevel(String riskLevel) {
        this.riskLevel = riskLevel;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedRiskLevel(RiskLevel source) {
        riskLevel = source.riskLevel;
    }

    @JsonValue
    public String getRiskLevel() {
        return riskLevel;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public RiskLevel toModelType() throws IllegalValueException {
        if (!RiskLevel.isValidRiskLevel(riskLevel)) {
            throw new IllegalValueException(RiskLevel.MESSAGE_CONSTRAINTS);
        }
        return new RiskLevel(riskLevel);
    }

}
