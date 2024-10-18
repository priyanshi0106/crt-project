import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

// Define the Recipe class
class Recipe {
    private String name;
    private String description;
    private ArrayList<String> ingredients;
    private String instructions;

    public Recipe(String name, String description, ArrayList<String> ingredients, String instructions) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }
}

// Define the RecipeBook class
class RecipeBook {
    private ArrayList<Recipe> recipes;

    public RecipeBook() {
        this.recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void listRecipes() {
        for (Recipe recipe : recipes) {
            System.out.println("Recipe: " + recipe.getName());
            System.out.println("Description: " + recipe.getDescription());
            System.out.println("Ingredients: " + recipe.getIngredients());
            System.out.println("Instructions: " + recipe.getInstructions());
            System.out.println();
        }
    }

    public Recipe searchRecipeByName(String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equalsIgnoreCase(name)) {
                return recipe;
            }
        }
        return null;
    }

    public ArrayList<Recipe> searchRecipesByIngredient(String ingredient) {
        ArrayList<Recipe> results = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getIngredients().contains(ingredient.trim())) {
                results.add(recipe);
            }
        }
        return results;
    }
}

public class RecipeSharingPlatform {  // Rename the main class
    public static void main(String[] args) {
        RecipeBook recipeBook = new RecipeBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add recipe");
            System.out.println("2. List recipes");
            System.out.println("3. Search recipe by name");
            System.out.println("4. Search recipes by ingredient");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            // Input validation for option selection
            int option;
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                scanner.nextLine(); // Clear the invalid input
                continue; // Restart the loop
            }

            switch (option) {
                case 1:
                    System.out.print("Enter recipe name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter recipe description: ");
                    String description = scanner.nextLine();
                    System.out.print("Enter recipe ingredients (comma separated): ");
                    String ingredientsStr = scanner.nextLine();
                    ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(ingredientsStr.split(",")));
                    // Trim whitespace from each ingredient
                    for (int i = 0; i < ingredients.size(); i++) {
                        ingredients.set(i, ingredients.get(i).trim());
                    }
                    System.out.print("Enter recipe instructions: ");
                    String instructions = scanner.nextLine();

                    Recipe recipe = new Recipe(name, description, ingredients, instructions);
                    recipeBook.addRecipe(recipe);
                    break;
                case 2:
                    recipeBook.listRecipes();
                    break;
                case 3:
                    System.out.print("Enter recipe name to search: ");
                    String searchName = scanner.nextLine();
                    Recipe searchRecipe = recipeBook.searchRecipeByName(searchName);
                    if (searchRecipe != null) {
                        System.out.println("Recipe found: ");
                        System.out.println(" Recipe: " + searchRecipe.getName());
                        System.out.println("Description: " + searchRecipe.getDescription());
                        System.out.println("Ingredients: " + searchRecipe.getIngredients());
                        System.out.println("Instructions: " + searchRecipe.getInstructions());
                    } else {
                        System.out.println("Recipe not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter ingredient to search: ");
                    String searchIngredient = scanner.nextLine();
                    ArrayList<Recipe> searchRecipes = recipeBook.searchRecipesByIngredient(searchIngredient);
                    if (!searchRecipes.isEmpty()) {
                        System.out.println("Recipes found with ingredient " + searchIngredient + ":");
                        for (Recipe r : searchRecipes) {
                            System.out.println("Recipe: " + r.getName());
                            System.out.println("Description: " + r.getDescription());
                            System.out.println("Ingredients: " + r.getIngredients());
                            System.out.println("Instructions: " + r.getInstructions());
                            System.out.println();
                        }
                    } else {
                        System.out.println("No recipes found with ingredient " + searchIngredient + ".");
                    }
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}