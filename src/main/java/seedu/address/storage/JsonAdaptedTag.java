package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.risklevel.RiskLevel;

/**
 * Jackson-friendly version of {@link RiskLevel}.
 */
class JsonAdaptedTag {

    private final String tagName;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code riskLevel}.
     */
    @JsonCreator
    public JsonAdaptedTag(String tagName) {
        this.tagName = tagName;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(RiskLevel source) {
        tagName = source.riskLevel;
    }

    @JsonValue
    public String getTagName() {
        return tagName;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public RiskLevel toModelType() throws IllegalValueException {
        if (!RiskLevel.isValidRiskLevel(tagName)) {
            throw new IllegalValueException(RiskLevel.MESSAGE_CONSTRAINTS);
        }
        return new RiskLevel(tagName);
    }

}
