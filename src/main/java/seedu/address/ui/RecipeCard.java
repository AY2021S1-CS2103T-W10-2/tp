package seedu.address.ui;

import java.util.Comparator;
import java.util.NoSuchElementException;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.recipe.Recipe;

/**
 * An UI component that displays information of a {@code Recipe}.
 */
public class RecipeCard extends UiPart<Region> {

    private static final String FXML = "RecipeListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on WishfulShrinking level 4</a>
     */

    public final Recipe recipe;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label ingredients;
    @FXML
    private Label calories;
    @FXML
    private Label instruction;
    @FXML
    private FlowPane tags;
    @FXML
    private ImageView recipeImage;


    /**
     * Creates a {@code RecipeCode} with the given {@code Recipe} and index to display.
     */
    public RecipeCard(Recipe recipe, int displayedIndex) {
        super(FXML);
        this.recipe = recipe;
        id.setText(displayedIndex + ". ");
        name.setText(recipe.getName().fullName);
        instruction.setText(recipe.getInstruction().stream()
                    .map(item -> item.toString() + ".\n")
                .reduce("", (a, b) -> a + b).trim());

        try {
            Image rawImage = new Image(recipe.getRecipeImage().getValue(), 340, 0, true, true);
            PixelReader reader = rawImage.getPixelReader();
            WritableImage newImage = new WritableImage(reader, 0, 0, 310, 150);
            recipeImage.setImage(newImage);
            recipeImage.setPreserveRatio(true);
        } catch (IllegalArgumentException | NoSuchElementException | NullPointerException ex) {
            recipe.setDefaultImage();
        } finally {
            Image rawImage = new Image(recipe.getRecipeImage().getValue(), 340, 0, true, true);
            PixelReader reader = rawImage.getPixelReader();
            WritableImage newImage = new WritableImage(reader, 0, 0, 310, 150);
            recipeImage.setImage(newImage);
            recipeImage.setPreserveRatio(true);
        }

        //Responsive resizing
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            recipeImage.setFitWidth(getRoot().getWidth() - 10);
        };
        getRoot().widthProperty().addListener(stageSizeListener);

        ingredients.setText(recipe.stringifyIngredients(recipe.getIngredient()));

        calories.setText(recipe.getCalories().value.toString() + " cal");

        this.recipe.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecipeCard)) {
            return false;
        }

        // state check
        RecipeCard card = (RecipeCard) other;
        return id.getText().equals(card.id.getText())
                && recipe.equals(card.recipe);
    }
}
