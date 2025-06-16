package javafxlearning;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.control.RadioButton;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PhysicalActivityManager extends Application {
    private final int HORIZONTAL_GAP = 10;
    private final int VERTICAL_GAP = 10;
    ComboBox<String> physicalActivityComboBox;
    TextField timeSpentField;
    Slider intensitySlider;
    Label calorieExpendedResultLabel;
    Label goalMetLabel;
    TextField expectedCalorieConsumptionField;
    Label showActivityLabel;
    Label showSliderLabel;
    VBox vbox_buttons;
    ToggleGroup dayOfWeekGroup;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Layout
        BorderPane borderPane = new BorderPane();
        MenuBar menuBar = new MenuBar();
        GridPane gridPane = new GridPane();
        vbox_buttons = new VBox(VERTICAL_GAP);
        VBox icon_gridPane_VBox = new VBox();
        borderPane.setTop(menuBar);
        borderPane.setCenter(icon_gridPane_VBox);
        borderPane.setBottom(vbox_buttons);
        gridPane.setPadding(new Insets(20));
        vbox_buttons.setPadding(new Insets(20));
        gridPane.setHgap(HORIZONTAL_GAP);
        gridPane.setVgap(VERTICAL_GAP);
        gridPane.setAlignment(Pos.CENTER);
        vbox_buttons.setAlignment(Pos.CENTER);



        // Form controls
        ImageView activityIcon = new ImageView(new Image("https://static.vecteezy.com/system/resources/thumbnails/007/873/039/small/sports-set-icon-illustration-healthy-physical-fitness-sports-equipment-free-vector.jpg"));
        HBox activityIconHBox = new HBox(activityIcon);
        activityIconHBox.setAlignment(Pos.CENTER);
        activityIconHBox.setPadding(new Insets(5));
        icon_gridPane_VBox.getChildren().addAll(activityIconHBox, gridPane);

        Label physicalActivityLabel = new Label("Select a physical activity:");
        physicalActivityComboBox = new ComboBox<String>();
        physicalActivityComboBox.getItems().addAll("Running", "Swimming", "Cycling", "Weights");
        gridPane.add(physicalActivityLabel, 0, 0);
        gridPane.add(physicalActivityComboBox, 1, 0);

        Label timeSpentLabel = new Label("Minutes spent on activity:");
        timeSpentField = new TextField();
        gridPane.add(timeSpentLabel, 0, 1);
        gridPane.add(timeSpentField, 1, 1);

        Label intensityLabel = new Label("Enter intensity of exercise:");
        intensitySlider = new Slider();
        intensitySlider.setShowTickMarks(true);
        intensitySlider.setShowTickLabels(true);
        intensitySlider.setSnapToTicks(true);
        intensitySlider.setMajorTickUnit(1);
        intensitySlider.setMinorTickCount(0);
        intensitySlider.setMax(10);
        intensitySlider.setBlockIncrement(1);
        gridPane.add(intensityLabel, 0, 2);
        gridPane.add(intensitySlider, 1, 2);

        showActivityLabel = new Label("Current activity: ");
        gridPane.add(showActivityLabel, 0, 3);
        showSliderLabel = new Label("Current intensity: ");
        gridPane.add(showSliderLabel, 1, 3);

        Label expectedCalorieConsumptionLabel = new Label("Expected calorie consumption:");
        expectedCalorieConsumptionField = new TextField();
        gridPane.add(expectedCalorieConsumptionLabel, 0, 4);
        gridPane.add(expectedCalorieConsumptionField, 1, 4);

        Label calorieExpendedLabel = new Label("Expected calorie expended:");
        calorieExpendedResultLabel = new Label();
        gridPane.add(calorieExpendedLabel, 0, 5);
        gridPane.add(calorieExpendedResultLabel, 1, 5);

        dayOfWeekGroup = new ToggleGroup();
        RadioButton sundayRadioButton = new RadioButton("Sunday");
        RadioButton mondayRadioButton = new RadioButton("Monday");
        RadioButton tuesdayRadioButton = new RadioButton("Tuesday");
        RadioButton wednesdayRadioButton = new RadioButton("Wednesday");
        RadioButton thursdayRadioButton = new RadioButton("Thursday");
        RadioButton fridayRadioButton = new RadioButton("Friday");
        RadioButton saturdayRadioButton = new RadioButton("Saturday");
        sundayRadioButton.setSelected(true);
        sundayRadioButton.setToggleGroup(dayOfWeekGroup);
        mondayRadioButton.setToggleGroup(dayOfWeekGroup);
        tuesdayRadioButton.setToggleGroup(dayOfWeekGroup);
        wednesdayRadioButton.setToggleGroup(dayOfWeekGroup);
        thursdayRadioButton.setToggleGroup(dayOfWeekGroup);
        fridayRadioButton.setToggleGroup(dayOfWeekGroup);
        saturdayRadioButton.setToggleGroup(dayOfWeekGroup);
        vbox_buttons.getChildren().addAll(sundayRadioButton, mondayRadioButton, tuesdayRadioButton, wednesdayRadioButton, thursdayRadioButton, fridayRadioButton, saturdayRadioButton);

        goalMetLabel = new Label("Did you meet your goal?");
        gridPane.add(goalMetLabel, 0, 6);

        Button submitButton = new Button("Submit information");
        gridPane.add(submitButton, 1, 6);



        // Menus
        Menu fileMenu = new Menu("_File");
        Menu helpMenu = new Menu("_Help");
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        MenuItem openFileMenuItem = new MenuItem("_Open file");
        MenuItem saveFileMenuItem = new MenuItem("_Save file");
        fileMenu.getItems().addAll(openFileMenuItem, saveFileMenuItem);

        MenuItem getHelpMenuItem = new MenuItem("Get _help ");
        helpMenu.getItems().add(getHelpMenuItem);



        // Event handlers
        physicalActivityComboBox.setOnAction(event -> {
            event.consume();
            showActivityLabel.setText("Current activity: " + physicalActivityComboBox.getValue());
        });

        intensitySlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            showSliderLabel.setText("Current intensity: " + (int) intensitySlider.getValue());
        });

        openFileMenuItem.setOnAction(event -> {
            event.consume();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Activity Data");

            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            openFile(selectedFile);
        });

        saveFileMenuItem.setOnAction(event -> {
            event.consume();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Activity Data");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

            File savedFile = fileChooser.showSaveDialog(primaryStage);

            saveFile(savedFile);
        });

        getHelpMenuItem.setOnAction(event -> {
            event.consume();
            Alert helpMenuAlert = new Alert(AlertType.INFORMATION);
            helpMenuAlert.setTitle("Help");
            helpMenuAlert.setHeaderText("How to use Physical Activity Manager");
            helpMenuAlert.setContentText("This app helps you track your weekly physical activities");
            helpMenuAlert.showAndWait();
        });

        submitButton.setOnAction(event -> {
            event.consume();
            calculateExpendedCalories();
        });



        // Scene & Stage
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Physical Activity Manager");
        primaryStage.show();
    }



    private void calculateExpendedCalories() {
        double expendedCalories = 0;
        double activityFactor = 1;
        double timeInMinutes = 1;
        double intensity = 1;

        String activityFactorAsString = physicalActivityComboBox.getSelectionModel().getSelectedItem();
        if (activityFactorAsString.equals("Running")) {
            activityFactor = 1.2;
        } else if (activityFactorAsString.equals("Swimming")) {
            activityFactor = 1.5;
        } else if (activityFactorAsString.equals("Cycling")) {
            activityFactor = 1.3;
        } else if (activityFactorAsString.equals("Weights")) {
            activityFactor = 1.7;
        }

        timeInMinutes = Double.parseDouble(timeSpentField.getText());
        intensity = intensitySlider.getValue();

        expendedCalories = activityFactor * timeInMinutes * intensity;
        calorieExpendedResultLabel.setText(Double.toString(expendedCalories));

        Double expectedCalories = Double.parseDouble(expectedCalorieConsumptionField.getText());

        if (expectedCalories < expendedCalories) {
            goalMetLabel.setText("Goal not met");
        } else {
            goalMetLabel.setText("Goal met!");
        }
    }



    private void openFile(File file) {
        String saveActivity = "";
        Double minutesSpent = 0.0;
        Double intensity = 0.0;
        Double expectedCalorie = 0.0;
        String dayOfWeek = "";

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");

                saveActivity = parts[0];
                minutesSpent = Double.parseDouble(parts[1]);
                intensity = Double.parseDouble(parts[2]);
                expectedCalorie = Double.parseDouble(parts[3]);
                dayOfWeek = parts[4];
            }

            physicalActivityComboBox.getSelectionModel().select(saveActivity);
            timeSpentField.setText(Double.toString(minutesSpent));
            intensitySlider.setValue(intensity);
            expectedCalorieConsumptionField.setText(Double.toString(expectedCalorie));
            for (Toggle toggle : dayOfWeekGroup.getToggles()) {
                // Selects the radio button with the day of week from the file
                RadioButton rb = (RadioButton) toggle;
                if (rb.getText().equals(dayOfWeek)) {
                    dayOfWeekGroup.selectToggle(rb);
                }
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }



    private void saveFile(File file) {
        String saveActivity = "";
        String minutesSpent = "";
        String intensity = "";
        String expectedCalorie = "";
        String dayOfWeek = "";

        saveActivity = physicalActivityComboBox.getSelectionModel().getSelectedItem();
        minutesSpent = timeSpentField.getText();
        intensity = Integer.toString((int) intensitySlider.getValue());
        expectedCalorie = expectedCalorieConsumptionField.getText();
        dayOfWeek = ((RadioButton) dayOfWeekGroup.getSelectedToggle()).getText();

        if (file != null) {
            try (PrintWriter writer = new PrintWriter(file)) {
                writer.println(saveActivity + "," + minutesSpent + "," + intensity + "," + expectedCalorie + "," + dayOfWeek);
            } catch (IOException error) {
                error.printStackTrace();
            }
        }
    }
}
