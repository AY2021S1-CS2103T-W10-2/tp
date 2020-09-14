package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.recipe.*;
import seedu.address.model.recipe.Recipe;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Recipe objects.
 */
public class RecipeBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Email email;
    private Address address;
    private Set<Tag> tags;

    /**
     * Creates a {@code RecipeBuilder} with the default details.
     */
    public RecipeBuilder() {
        name = new Name(DEFAULT_NAME);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the RecipeBuilder with the data of {@code RecipeToCopy}.
     */
    public RecipeBuilder(Recipe recipeToCopy) {
        name = recipeToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Recipe} that we are building.
     */
    public RecipeBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Recipe} that we are building.
     */
    public RecipeBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    public Recipe build() {
        return new Recipe(name);
    }

}
