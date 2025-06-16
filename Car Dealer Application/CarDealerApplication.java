// ProjectGroup6
// Group Members: Lily Acconey, Xavier Cosme, Seven Laughton-Stuart

package javafxlearning;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.text.Font;
import java.util.Random;

public class FinalProjectGROUPMATE extends Application {

    private TextField accountNumberField;
    private TextField nameField;
    private TextField addressField;
    private TextField phoneField;
    private ComboBox<String> carModelComboBox;
    private CheckBox packageBCheckbox;
    private CheckBox metallicPaintCheckbox;
    private TextField tradeInField;
    private RadioButton financingRadio;
    private RadioButton cashRadio;
    private Label finalCostLabel;

    @Override
    public void start(Stage primaryStage) {
        try {
            GridPane grid = new GridPane();
            grid.setVgap(15);
            grid.setHgap(10);
            grid.setPadding(new Insets(30));
            grid.setAlignment(Pos.TOP_CENTER);
            grid.setBackground(new Background(new BackgroundFill(Color.web("#f5e6ca"), CornerRadii.EMPTY, Insets.EMPTY)));

            Font labelFont = new Font("Arial", 14);

            Label accountLabel = new Label("Account Number:");
            accountLabel.setFont(labelFont);
            accountNumberField = new TextField();
            accountNumberField.setEditable(false);
            accountNumberField.setText(generateAccountNumber());

            grid.add(accountLabel, 0, 0);
            grid.add(accountNumberField, 1, 0);

            Label customerInfoLabel = new Label("Customer Information");
            customerInfoLabel.setFont(labelFont);
            grid.add(customerInfoLabel, 0, 1);

            Label nameLabel = new Label("Name:");
            nameLabel.setFont(labelFont);
            nameField = new TextField();
            grid.add(nameLabel, 0, 2);
            grid.add(nameField, 1, 2);

            Label addressLabel = new Label("Address:");
            addressLabel.setFont(labelFont);
            addressField = new TextField();
            grid.add(addressLabel, 0, 3);
            grid.add(addressField, 1, 3);

            Label phoneLabel = new Label("Phone Number:");
            phoneLabel.setFont(labelFont);
            phoneField = new TextField();
            grid.add(phoneLabel, 0, 4);
            grid.add(phoneField, 1, 4);

            Label carModelLabel = new Label("Select Car Model:");
            carModelLabel.setFont(labelFont);
            carModelComboBox = new ComboBox<>();
            carModelComboBox.getItems().addAll(
                    "S40 - $27700",
                    "S60 - $32500",
                    "S70 - $36000",
                    "S80 - $44000"
            );
            carModelComboBox.setPromptText("Select Model");
            grid.add(carModelLabel, 0, 5);
            grid.add(carModelComboBox, 1, 5);

            Label optionsLabel = new Label("Options:");
            optionsLabel.setFont(labelFont);
            packageBCheckbox = new CheckBox("Package B ($3250) - Only for S70 and S80");
            metallicPaintCheckbox = new CheckBox("Metallic Paint ($650)");
            grid.add(optionsLabel, 0, 6);
            grid.add(packageBCheckbox, 1, 6);
            grid.add(metallicPaintCheckbox, 1, 7);

            Label tradeInLabel = new Label("Trade-In Amount:");
            tradeInLabel.setFont(labelFont);
            tradeInField = new TextField();
            grid.add(tradeInLabel, 0, 8);
            grid.add(tradeInField, 1, 8);
            // grid.setGridLinesVisible(true);  //!! added myself

            Label paymentLabel = new Label("Payment Method:");
            paymentLabel.setFont(labelFont);
            financingRadio = new RadioButton("Finance (Add 7%)");
            cashRadio = new RadioButton("Cash (Discount $750)");

            ToggleGroup paymentGroup = new ToggleGroup();
            financingRadio.setToggleGroup(paymentGroup);
            cashRadio.setToggleGroup(paymentGroup);

            grid.add(paymentLabel, 0, 9);
            grid.add(financingRadio, 1, 9);
            grid.add(cashRadio, 1, 10);

            Button calculateButton = new Button("Calculate Final Cost");
            calculateButton.setPrefWidth(200);
            finalCostLabel = new Label("Final Cost: ");
            finalCostLabel.setFont(labelFont);

            grid.add(calculateButton, 0, 11);
            grid.add(finalCostLabel, 1, 11);

            calculateButton.setOnAction(e -> calculateFinalCost());

            Button exitButton = new Button("Exit");
            exitButton.setPrefWidth(100);
            GridPane.setMargin(exitButton, new Insets(30, 0, 0, 0));
            grid.add(exitButton, 1, 13);

            exitButton.setOnAction(e -> primaryStage.close());

            ScrollPane scrollPane = new ScrollPane(grid);
            scrollPane.setFitToWidth(true);
            scrollPane.setBackground(new Background(new BackgroundFill(Color.web("#f5e6ca"), CornerRadii.EMPTY, Insets.EMPTY))); // Full background

            Scene scene = new Scene(grid, 535, 575);
            primaryStage.setTitle("Car Dealer Application");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculateFinalCost() {
        double basePrice = 0.0;

        if (carModelComboBox.getValue() != null) {
            if (carModelComboBox.getValue().contains("S40")) basePrice = 27700;
            else if (carModelComboBox.getValue().contains("S60")) basePrice = 32500;
            else if (carModelComboBox.getValue().contains("S70")) basePrice = 36000;
            else if (carModelComboBox.getValue().contains("S80")) basePrice = 44000;
        }

        if (packageBCheckbox.isSelected() && carModelComboBox.getValue() != null &&
                (carModelComboBox.getValue().contains("S70") || carModelComboBox.getValue().contains("S80"))) {
            basePrice += 3250;
        }
        if (metallicPaintCheckbox.isSelected()) {
            basePrice += 650;
        }

        double totalPrice = basePrice * 1.06 + 325;

        try {
            double tradeIn = Double.parseDouble(tradeInField.getText());
            totalPrice -= tradeIn;
        } catch (Exception ex) {
            // Ignore
        }

        if (financingRadio.isSelected()) {
            totalPrice *= 1.07;
        } else if (cashRadio.isSelected()) {
            totalPrice -= 750;
        }

        finalCostLabel.setText(String.format("Final Cost: $%.2f", totalPrice));
    }

    private String generateAccountNumber() {
        Random rand = new Random();
        int number = rand.nextInt(900000) + 100000;
        return String.valueOf(number);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

