package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.risklevel.RiskLevel;
import seedu.address.model.util.LimitedHashSet;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;

    // Data fields
    private final Address address;

    // Each student should only have 1 risk level
    private final Set<RiskLevel> riskLevel = new LimitedHashSet<>(1);
    private final Note note;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Address address, Set<RiskLevel> riskLevel, Note note) {
        requireAllNonNull(name, phone, address, riskLevel);
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.riskLevel.addAll(riskLevel);
        this.note = note;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<RiskLevel> getTags() {
        return Collections.unmodifiableSet(riskLevel);
    }

    public Note getNote() {
        return note;
    }

    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName());
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return name.equals(otherStudent.name)
                && phone.equals(otherStudent.phone)
                && address.equals(otherStudent.address)
                && riskLevel.equals(otherStudent.riskLevel);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, address, riskLevel);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("address", address)
                .add("tags", riskLevel)
                .add("note", note)
                .toString();
    }

}
