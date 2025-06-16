package javafxlearning;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class GPACalculator extends Application {
    TextField course1Credits, course1Grade;
    TextField course2Credits, course2Grade;
    TextField course3Credits, course3Grade;
    TextField course4Credits, course4Grade;
    Label calculatedGPA;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Containers
        BorderPane borderpane = new BorderPane();
        GridPane formGrid = new GridPane();
        VBox buttonVBox = new VBox(5);
        buttonVBox.setAlignment(Pos.CENTER);
        borderpane.setCenter(formGrid);
        borderpane.setBottom(buttonVBox);
        borderpane.setPadding(new Insets(30));
        formGrid.setHgap(5);
        formGrid.setVgap(5);
        formGrid.setAlignment(Pos.CENTER);


        // Controls
        Label nameLabel = new Label("Student Name:");
        TextField nameField = new TextField();
        formGrid.add(nameLabel, 0, 0);
        formGrid.add(nameField, 1, 0);

        Label creditsLabel = new Label("Credits");
        Label courseLabel = new Label("Grade");
        formGrid.add(creditsLabel, 1, 1);
        formGrid.add(courseLabel, 2, 1);

        Label course1Label = new Label("COP1000C:");
        course1Credits = new TextField();
        course1Grade = new TextField();
        formGrid.add(course1Label, 0, 2);
        formGrid.add(course1Credits, 1, 2);
        formGrid.add(course1Grade, 2, 2);

        Label course2Label = new Label("COP1250C:");
        course2Credits = new TextField();
        course2Grade = new TextField();
        formGrid.add(course2Label, 0, 3);
        formGrid.add(course2Credits, 1, 3);
        formGrid.add(course2Grade, 2, 3);

        Label course3Label = new Label("COP1334C:");
        course3Credits = new TextField();
        course3Grade = new TextField();
        formGrid.add(course3Label, 0, 4);
        formGrid.add(course3Credits, 1, 4);
        formGrid.add(course3Grade, 2, 4);

        Label course4Label = new Label("COP2251C:");
        course4Credits = new TextField();
        course4Grade = new TextField();
        formGrid.add(course4Label, 0, 5);
        formGrid.add(course4Credits, 1, 5);
        formGrid.add(course4Grade, 2, 5);

        calculatedGPA = new Label();
        formGrid.add(calculatedGPA, 2, 6);

        Button calculateButton = new Button("Calculate GPA");
        Button clearButton = new Button("Clear");

        // Adds all buttons to the button VBox container
        buttonVBox.getChildren().addAll(calculateButton, clearButton);

        calculateButton.setOnAction(new CalculateGPAHandler());

        clearButton.setOnAction(event -> {
            // Clears all TextFields
            nameField.clear();
            course1Credits.clear();
            course1Grade.clear();
            course2Credits.clear();
            course2Grade.clear();
            course3Credits.clear();
            course3Grade.clear();
            course4Credits.clear();
            course4Grade.clear();
            calculatedGPA.setText("");
        });

        // Set borderpane top image
        ImageView universityLogo = new ImageView(new Image("https://upload.wikimedia.org/wikipedia/commons/thumb/c/c1/Logo-of-Broward-College.svg/2560px-Logo-of-Broward-College.svg.png"));
        universityLogo.setFitWidth(400);
        universityLogo.setPreserveRatio(true);
        HBox hbox_universityLogo = new HBox(universityLogo);
        hbox_universityLogo.setAlignment(Pos.CENTER);
        hbox_universityLogo.setPadding(new Insets(0, 0, 30, 0));
        borderpane.setTop(hbox_universityLogo);


        // Scene & Stage
        Scene scene = new Scene(borderpane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student GPA Calculator");
        primaryStage.show();
    }

    class CalculateGPAHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            // Calculate GPA Logic

            // Get all the grades and credits for each class
            Double course1Creds = Double.parseDouble(course1Credits.getText());
            Double course1Grad = convertToGradePoint(Double.parseDouble(course1Grade.getText()));
            Double course2Creds = Double.parseDouble(course2Credits.getText());
            Double course2Grad = convertToGradePoint(Double.parseDouble(course2Grade.getText()));
            Double course3Creds = Double.parseDouble(course3Credits.getText());
            Double course3Grad = convertToGradePoint(Double.parseDouble(course3Grade.getText()));
            Double course4Creds = Double.parseDouble(course4Credits.getText());
            Double course4Grad = convertToGradePoint(Double.parseDouble(course4Grade.getText()));

            // Calculate GPA
            Double totalGradePoint = (course1Creds * course1Grad) + (course2Creds * course2Grad) + (course3Creds * course3Grad) + (course4Creds * course4Grad);
            Double totalCreditHours = course1Creds + course2Creds + course3Creds + course4Creds;
            Double finalGPA = totalGradePoint / totalCreditHours;

            // Set the GPA label to the correct one
            calculatedGPA.setText(String.format("GPA: %,.4f", finalGPA));
        }
    }

    private static double convertToGradePoint(double grade) {
        if (grade >= 93) return 4.0;
        else if (grade >= 90) return 3.7;
        else if (grade >= 87) return 3.4;
        else if (grade >= 83) return 3.2;
        else if (grade >= 80) return 3.0;
        else if (grade >= 77) return 2.7;
        else if (grade >= 73) return 2.4;
        else if (grade >= 70) return 2.0;
        else if (grade >= 67) return 1.7;
        else if (grade >= 65) return 1.3;
        else if (grade >= 63) return 1.0;
        else if (grade >= 60) return 0.5;
        else return 0.0;

    }
}
