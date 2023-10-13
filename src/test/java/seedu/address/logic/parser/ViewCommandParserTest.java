package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class ViewCommandParserTest {
    private ViewCommandParser parser = new ViewCommandParser();
    private final String category = "appointments";

    @Test
    public void parse_indexSpecified_success() {
        // have remark
        String userInput = " " + PREFIX_CATEGORY + category;
        ViewCommand expectedCommand = new ViewCommand(category);
        assertParseSuccess(parser, userInput, expectedCommand);

        // no remark
        userInput = " " + PREFIX_CATEGORY + "";
        expectedCommand = new ViewCommand("");
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE);
        // no parameters
        assertParseFailure(parser, "", expectedMessage);
    }
}
