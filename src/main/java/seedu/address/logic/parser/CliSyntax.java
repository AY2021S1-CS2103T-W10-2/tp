package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_INGREDIENT = new Prefix("i/");
    public static final Prefix PREFIX_QUANTITY = new Prefix("-");
    public static final Prefix PREFIX_INSTRUCTION = new Prefix("instr/");
    public static final Prefix PREFIX_RECIPE_IMAGE = new Prefix("img/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_CALORIES = new Prefix("c/");

}
