package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;

import java.util.ArrayList;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddIngredientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.Ingredient;
//import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddIngredientCommand object
 */
public class AddIngredientCommandParser implements Parser<AddIngredientCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddIngredientCommand
     * and returns an AddIngredientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddIngredientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_INGREDIENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_INGREDIENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddIngredientCommand.MESSAGE_USAGE));
        }

        String ingredientString = ParserUtil.parseIngredient(argMultimap.getValue(PREFIX_INGREDIENT).get());
        ArrayList<Ingredient> ingredients = IngredientParser.parse(ingredientString);

        return new AddIngredientCommand(ingredients);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
