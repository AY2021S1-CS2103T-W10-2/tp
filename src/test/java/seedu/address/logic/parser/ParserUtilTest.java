package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ingredient.Ingredient;
import seedu.address.model.recipe.Name;
import seedu.address.model.recipe.Tag;

//import seedu.address.model.ingredient.Ingredient;

public class ParserUtilTest {
    private static final String INVALID_NAME = "R@tatouille";
    private static final String INVALID_INGREDIENT_NAME = "+apples";

    //does not match VALIDATION_REGEX_QUANTITY
    private static final String INVALID_INGREDIENT_QUANTITY_0 = "20&g";
    private static final String INVALID_INGREDIENT_QUANTITY_1 = "2.5/3.5g";
    private static final String INVALID_INGREDIENT_QUANTITY_2 = "2//5g";
    private static final String INVALID_INGREDIENT_QUANTITY_3 = "2..5g";
    //not a valid quantity value
    private static final String INVALID_INGREDIENT_QUANTITY_4 = "0" +
            ".0000000000000000000000000000000000000000000005 g"; //46 decimal places not supported by float

    private static final String INVALID_TAG = "#less calories";

    private static final String VALID_NAME = "Ratatouille";
    private static final String VALID_INGREDIENT_NAME = "Potato";
    private static final String VALID_INGREDIENT_QUANTITY = "250.0 g";
    //45 decimal places supported by float
    private static final String VALID_INGREDIENT_QUANTITY_1 = "0.000000000000000000000000000000000000000000005 g";
    private static final String VALID_INGREDIENT = VALID_INGREDIENT_NAME + " -" + VALID_INGREDIENT_QUANTITY;
    private static final String VALID_INGREDIENT_1 = VALID_INGREDIENT_NAME + " -" + VALID_INGREDIENT_QUANTITY_1;
    private static final String VALID_TAG_1 = "healthy";
    private static final String VALID_TAG_2 = "yummy";

    private static final String INVALID_INGREDIENT = INVALID_INGREDIENT_NAME + VALID_INGREDIENT_QUANTITY;
    private static final String INVALID_INGREDIENT_0 = VALID_INGREDIENT_NAME + INVALID_INGREDIENT_QUANTITY_0;
    private static final String INVALID_INGREDIENT_1 = VALID_INGREDIENT_NAME + INVALID_INGREDIENT_QUANTITY_1;
    private static final String INVALID_INGREDIENT_2 = VALID_INGREDIENT_NAME + INVALID_INGREDIENT_QUANTITY_2;
    private static final String INVALID_INGREDIENT_3 = VALID_INGREDIENT_NAME + INVALID_INGREDIENT_QUANTITY_3;
    private static final String INVALID_INGREDIENT_4 = VALID_INGREDIENT_NAME + INVALID_INGREDIENT_QUANTITY_4;

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_RECIPE, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_RECIPE, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseIngredient_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIngredient((String) null));
    }

    @Test
    public void parseIngredient_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIngredient(INVALID_INGREDIENT));
        assertThrows(ParseException.class, () -> ParserUtil.parseIngredient(INVALID_INGREDIENT_0));
        assertThrows(ParseException.class, () -> ParserUtil.parseIngredient(INVALID_INGREDIENT_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseIngredient(INVALID_INGREDIENT_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseIngredient(INVALID_INGREDIENT_3));
        assertThrows(ParseException.class, () -> ParserUtil.parseIngredient(INVALID_INGREDIENT_4));
    }

    @Test
    public void parseIngredient_validValueWithoutWhitespace_returnsIngredient() throws Exception {
        Ingredient expectedIngredient = new Ingredient(VALID_INGREDIENT);
        assertEquals(expectedIngredient.toString(), ParserUtil.parseIngredient(VALID_INGREDIENT));
        Ingredient expectedIngredient1 = new Ingredient(VALID_INGREDIENT_1);
        assertEquals(expectedIngredient1.toString(), ParserUtil.parseIngredient(VALID_INGREDIENT_1));
    }

    @Test
    public void parseIngredient_validValueWithWhitespace_returnsTrimmedIngredient() throws Exception {
        String ingredientsWithWhitespace = WHITESPACE + VALID_INGREDIENT + WHITESPACE;
        Ingredient expectedIngredient = new Ingredient(VALID_INGREDIENT);
        assertEquals(expectedIngredient.toString(), ParserUtil.parseIngredient(ingredientsWithWhitespace));
    }

    @Test
    public void parseQuantity_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseIngredient((String) null));
    }

    @Test
    public void parseQuantity_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_INGREDIENT_QUANTITY_0));
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_INGREDIENT_QUANTITY_1));
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_INGREDIENT_QUANTITY_2));
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_INGREDIENT_QUANTITY_3));
        assertThrows(ParseException.class, () -> ParserUtil.parseQuantity(INVALID_INGREDIENT_QUANTITY_4));
    }

    @Test
    public void parseQuantity_validValueWithoutWhitespace_returnsIngredient() throws Exception {
        assertEquals(VALID_INGREDIENT_QUANTITY, ParserUtil.parseQuantity(VALID_INGREDIENT_QUANTITY));
        assertEquals(VALID_INGREDIENT_QUANTITY_1, ParserUtil.parseQuantity(VALID_INGREDIENT_QUANTITY_1));
    }

    @Test
    public void parseQuantity_validValueWithWhitespace_returnsTrimmedIngredient() throws Exception {
        String quantityWithWhitespace = WHITESPACE + VALID_INGREDIENT_QUANTITY + WHITESPACE;
        assertEquals(VALID_INGREDIENT_QUANTITY, ParserUtil.parseQuantity(quantityWithWhitespace));
    }

    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtil.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtil.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtil.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
}
