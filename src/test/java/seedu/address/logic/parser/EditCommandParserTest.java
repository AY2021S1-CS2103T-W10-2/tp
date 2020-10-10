package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INGREDIENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_RECIPE;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_RECIPE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditRecipeDescriptor;
import seedu.address.testutil.EditRecipeDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 prefix/ string", MESSAGE_INVALID_FORMAT);
    }

    /*@Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_INGREDIENT_DESC,
        //IngredientString.MESSAGE_CONSTRAINTS); // invalid ingredients
        assertParseFailure(parser, "1" + INVALID_TAG_DESC,
         Tag.MESSAGE_CONSTRAINTS); // invalid tag

        //invalid ingredients followed by valid email
        assertParseFailure(parser, "1" + INVALID_INGREDIENT_DESC
        + EMAIL_DESC_AMY, IngredientString.MESSAGE_CONSTRAINTS);

        // valid ingredients followed by invalid ingredients.
        //The test case for invalid ingredients followed by valid ingredients
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + INGREDIENT_DESC_BOB
        + INVALID_INGREDIENT_DESC, IngredientString.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags
        // of the {@code Recipe} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND
        + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND
        + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND
        + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC
        + INVALID_INGREDIENT_DESC + VALID_INGREDIENT_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }*/

    /*@Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_RECIPE;
        String userInput = targetIndex.getOneBased() + INGREDIENT_DESC_BOB
                + CALORIES_DESC_BOB + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB
                + TAG_DESC_BOB;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withName(VALID_NAME_AMY)
                .withIngredient(VALID_INGREDIENT_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }*/

    /*@Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + INGREDIENT_DESC_BOB
                + CALORIES_DESC_BOB + INSTRUCTION_DESC_BOB + RECIPE_IMAGE_DESC_BOB
                + TAG_DESC_BOB;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withIngredient(VALID_INGREDIENT_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }*/

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // ingredients
        userInput = targetIndex.getOneBased() + INGREDIENT_DESC_AMY;
        descriptor = new EditRecipeDescriptorBuilder().withIngredient(VALID_INGREDIENT_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        // assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_BOB;
        descriptor = new EditRecipeDescriptorBuilder().build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        // assertParseSuccess(parser, userInput, expectedCommand);
    }

    /*@Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + INGREDIENT_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + TAG_DESC_FRIEND + INGREDIENT_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + TAG_DESC_FRIEND
                + INGREDIENT_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB + TAG_DESC_HUSBAND;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().withIngredient(VALID_INGREDIENT_BOB)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }*/

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_RECIPE;
        String userInput = targetIndex.getOneBased() + INVALID_INGREDIENT_DESC
                + INGREDIENT_DESC_BOB;
        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder()
                .withIngredient(VALID_INGREDIENT_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        // assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INVALID_INGREDIENT_DESC
                + INGREDIENT_DESC_BOB;
        descriptor = new EditRecipeDescriptorBuilder().withIngredient(VALID_INGREDIENT_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        // assertParseSuccess(parser, userInput, expectedCommand);
    }

    /*@Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_RECIPE;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditRecipeDescriptor descriptor = new EditRecipeDescriptorBuilder().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }*/
}
