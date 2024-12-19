package Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LocatorReader {

    private static final Map<String, JsonNode> locatorsMap = new HashMap<>();

    // Load the JSON file once when the class is initialized
    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Parse the JSON file into an array of objects
            JsonNode locatorsArray = mapper.readTree(new File("src/test/resources/Locators/Locators.json"));

            // Validate for duplicate locator names and add to the map
            validateForDuplicateNames(locatorsArray);
            addLocatorsToMap(locatorsArray);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load locators JSON file", e);
        }
    }

    // Method to validate for duplicate locator names
    private static void validateForDuplicateNames(JsonNode locatorsArray) {
        Set<String> locatorNames = new HashSet<>();
        for (JsonNode locator : locatorsArray) {
            String locatorName = locator.get("LocatorName").asText();
            if (!locatorNames.add(locatorName)) {
                String errorMessage = "Duplicate locator name found: " + locatorName;
                System.out.println(errorMessage);  // Print the error to the console
                throw new IllegalArgumentException(errorMessage);  // Throw exception with the error message
            }
        }
    }

    // Method to add locators to a map
    private static void addLocatorsToMap(JsonNode locatorsArray) {
        for (JsonNode locator : locatorsArray) {
            String locatorName = locator.get("LocatorName").asText();
            locatorsMap.put(locatorName, locator);
        }
    }

    // Method to retrieve a locator as a By object
    public static By getLocator(String locatorName) {
        JsonNode locatorNode = locatorsMap.get(locatorName);

        if (locatorNode == null) {
            throw new IllegalArgumentException("Locator not found: " + locatorName);
        }

        String type = locatorNode.get("type").asText();
        String value = locatorNode.get("value").asText();

        // Optional: If you want to print the locator details for logging
       // System.out.println("Locator Name: " + locatorName + ", Type: " + type + ", Value: " + value);

        return switch (type.toLowerCase()) {
            case "id" -> By.id(value);
            case "name" -> By.name(value);
            case "xpath" -> By.xpath(value);
            case "css" -> By.cssSelector(value);
            case "classname" -> By.className(value);
            case "tagname" -> By.tagName(value);
            case "linktext" -> By.linkText(value);
            case "partiallinktext" -> By.partialLinkText(value);
            default -> throw new IllegalArgumentException("Unsupported locator type: " + type);
        };
    }
}
