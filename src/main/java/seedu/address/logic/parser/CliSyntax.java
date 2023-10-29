package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("c/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_RISK_LEVEL = new Prefix("r/");
    public static final Prefix PREFIX_CATEGORY = new Prefix("g/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("d/");
    public static final Prefix PREFIX_DATE = new Prefix("date/");
    public static final Prefix PREFIX_START_TIME = new Prefix("from/");
    public static final Prefix PREFIX_END_TIME = new Prefix("to/");
    public static final Prefix PREFIX_NOTE = new Prefix("note/");
}
