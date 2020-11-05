package seedu.address.logic.parser.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.ProviderNotFoundException;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.recipe.RecipeImage;

public class ImageParserTest {
    private static final String VALID_SAMPLE_IMAGE_STRING = "images/healthy1.jpg";
    private static final String VALID_LOCAL_IMAGE_STRING = "file:///C:/Users/Qi/data/food.jpg";
    private static final String VALID_URL_IMAGE_STRING =
            "https://www.onceuponachef.com/images/2019/07/Big-Italian-Salad.jpg";
    private static final String INVALID_LOCAL_IMAGE_STRING = "file:///invalidfilepath.jpg";

    @Test
    public void parse_validSamplePath_success() throws ParseException, IOException, URISyntaxException {
        RecipeImage expectedImage = new RecipeImage("images/healthy1.jpg");
        ImageParser imageParser = new ImageParser();
        assertEquals(expectedImage, imageParser.parse(VALID_SAMPLE_IMAGE_STRING));
    }

    @Test
    public void parse_validLocalPath_success() throws ParseException, IOException, URISyntaxException {
        RecipeImage expectedImage = new RecipeImage("file:///C:/Users/Qi/data/food.jpg");
        ImageParser imageParser = new ImageParser();
        assertEquals(expectedImage, imageParser.parse(VALID_LOCAL_IMAGE_STRING));
    }

    @Test
    public void parse_validUrlDifferentComputers_failure() throws ParseException, IOException, URISyntaxException {
        RecipeImage expectedImage = new RecipeImage(
                "file:///D:/Users/Documents/tp/build/classes/java/main/data/Big-Italian-Salad.jpg");
        ImageParser imageParser = new ImageParser();
        assertFalse(expectedImage.equals(imageParser.parse(VALID_URL_IMAGE_STRING)));
    }

    @Test
    public void getPathsFromResourceJar_validPathDifferentComputer_failure() throws URISyntaxException, IOException {
        ImageParser imageParser = new ImageParser();
        assertThrows(ProviderNotFoundException.class, () -> imageParser
                .getPathsFromResourceJar(INVALID_LOCAL_IMAGE_STRING));
    }

}
