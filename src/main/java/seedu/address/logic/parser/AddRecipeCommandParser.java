package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIES;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INGREDIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INSTRUCTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_IMAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

//import java.util.Set;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddRecipeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Calories;
import seedu.address.model.recipe.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddRecipeCommand object
 */
public class AddRecipeCommandParser implements Parser<AddRecipeCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddRecipeCommand
     * and returns an AddRecipeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddRecipeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_INGREDIENT, PREFIX_CALORIES,
                        PREFIX_INSTRUCTION, PREFIX_RECIPE_IMAGE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INGREDIENT, PREFIX_CALORIES,
                PREFIX_INSTRUCTION, PREFIX_RECIPE_IMAGE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddRecipeCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        String ingredientString = ParserUtil.parseIngredient(argMultimap.getValue(PREFIX_INGREDIENT).get());
        String[] ingredientsToken = ingredientString.split(",");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < ingredientsToken.length; i++) {
            ingredients.add(new Ingredient(ingredientsToken[i].trim()));
        }
        Calories calories = ParserUtil.parseCalories(argMultimap.getValue(PREFIX_CALORIES).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        String instruction = argMultimap.getValue(PREFIX_INSTRUCTION).get();
        String recipeImage = argMultimap.getValue(PREFIX_RECIPE_IMAGE).get();


        Recipe recipe = new Recipe(name, instruction, recipeImage, ingredients, calories, tagList);

        return new AddRecipeCommand(recipe);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
