package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commons.Calories;
import seedu.address.model.recipe.Name;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_CONSTRAINTS =
            "Ingredients should be separated by commas, "
                    + "and each ingredient should be consisted "
                    + "of alphanumeric characters only";
    public static final String VALIDATION_REGEX = "[\\w\\s]+(,\\s*[\\w\\s]*)*";
    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    public static boolean isValidIngredient(String ingredientString) {
        return ingredientString.matches(VALIDATION_REGEX);
    }

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String ingredients} into a {@code Ingredient}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code ingredients} is invalid.
     */
    public static String parseIngredient(String ingredients) throws ParseException {
        requireNonNull(ingredients);
        String trimmedIngredient = ingredients.trim();
        if (!trimmedIngredient.matches(VALIDATION_REGEX)) {
            throw new ParseException(MESSAGE_CONSTRAINTS);
        }
        return trimmedIngredient;
    }

    /**
     * Parses a {@code String calories} into an {@code Calories}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code calories} is invalid.
     */
    public static Calories parseCalories(String calories) throws ParseException {
        requireNonNull(calories);
        String trimmedCalories = calories.trim();
        if (!Calories.isValidCalories(trimmedCalories)) {
            throw new ParseException(Calories.MESSAGE_CONSTRAINTS);
        }
        return new Calories(Integer.parseInt(trimmedCalories));
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }
}
