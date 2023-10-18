package seedu.address.testutil;

import seedu.address.model.WellNus;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private WellNus wellNus;

    public AddressBookBuilder() {
        wellNus = new WellNus();
    }

    public AddressBookBuilder(WellNus wellNus) {
        this.wellNus = wellNus;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        wellNus.addPerson(person);
        return this;
    }

    public WellNus build() {
        return wellNus;
    }
}
