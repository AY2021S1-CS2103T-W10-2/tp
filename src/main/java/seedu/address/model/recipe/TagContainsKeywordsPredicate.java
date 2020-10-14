package seedu.address.model.recipe;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Recipe}'s {@code tags} matches any of the keywords given.
 */
public class TagContainsKeywordsPredicate extends RecipeContainsKeywordsPredicate
        implements Predicate<Recipe> {

    public TagContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Recipe recipe) {
        String tags = recipe.getTags().stream().map(Object::toString).collect(Collectors.joining(","));
        //String tags = String.join("-", recipe.getTags());
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(tags, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }

}
