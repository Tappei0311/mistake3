package org.example.cab302_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.sql.Connection;

//test
public class IngredientTrackerApplication extends Application {

    // Constants which will define the window title and size
    public static final String TITLE = "Ingredient Tracker";
    public static final int WIDTH = 340;
    public static final int HEIGHT = 450;
    private IngredientsDAO ingredientsDAO = new IngredientsDAO();
    private RecipeDAO recipeDAO = new RecipeDAO();
    private UserDAO userDAO = new UserDAO();
//    // Background and text colouring constants
//    public static final String BACKGROUND_STYLING = "-fx-background-color: #2B2B2B;";
//    public static final String TEXT_STYLING = "-fx-text-fill: white;";

    @Override
    public void init() {
        ingredientsDAO.createTable();
        recipeDAO.createRecipeTable();
        recipeDAO.createRecipeIngredientTable();
        userDAO.createUserTable();
    }



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(IngredientTrackerApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        // Get the LoginController and set the UserDAO
        LoginController loginController = fxmlLoader.getController();
        loginController.setUserDAO(userDAO);

//        // Apply background color to the root pane
//        Pane rootPane = (Pane) scene.getRoot();
//        rootPane.setStyle(BACKGROUND_STYLING); // Set background color
//
//        // Apply text color globally
//        scene.getRoot().lookupAll(".label").forEach(node -> node.setStyle(TEXT_STYLING));
//        scene.getRoot().lookupAll(".button").forEach(node -> node.setStyle(TEXT_STYLING));

        // Add the stylesheet to the scene
        scene.getStylesheets().add(Objects.requireNonNull(IngredientTrackerApplication.class.getResource("FormStyles.css")).toExternalForm());

        stage.setTitle(TITLE);
        stage.setScene(scene);
        stage.show();

        Connection connection = DatabaseConnection.getInstance();


    }

    public static void main(String[] args) {
        launch();
    }
}