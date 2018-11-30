// Tommy Poon
// CISC 124 Fall 2018
// NET-ID 14tchp
package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class PizzaController {

	// Pizza Attributes
	private String size;
	private String cheese;
	private String pineapple;
	private String greenPepper;
	private String ham;
	private int quantity;

	private double totalPizzasCost = 0;
	private Pizza pizza;

	@FXML
	private ChoiceBox<String> sizeChoiceBox;

	@FXML
	private ChoiceBox<String> cheeseChoiseBox;

	@FXML
	private CheckBox hamCheckBox;

	@FXML
	private CheckBox pineappleCheckBox;

	@FXML
	private CheckBox greenPepperCheckBox;

	@FXML
	private Label costPerPizza;

	@FXML
	private Slider quantitySlider;

	@FXML
	private Label quantityLabel;

	@FXML
	private Label totalCost;

	@FXML
	private Button addLineItemButton;

	@FXML
	private Label lineItemsTotalCost;

	@FXML
	private TextArea lineItemTextArea;

	@FXML
	private TextArea lineItemCostTextArea;

	@FXML
	private Label discountLabel;

	// creates a pizza and updates costs
	private void createPizza() {
		try {
			pizza = new Pizza(size, cheese, pineapple, greenPepper, ham);
			Double cost = pizza.getCost();
			costPerPizza.setText("$" + String.format("%.2f", cost));
			totalCost.setText("$" + String.format("%.2f", calculateTotalCost()));
		} catch (IllegalPizza e) {
			// TODO: handle exception
			System.err.println(e);
			costPerPizza.setText("Illegal Pizza");
			totalCost.setText("Illegal Pizza");
		}
	}

	// listens for changes in the choice boxes and recreates the pizza
	private void choiceBoxListeners() {
		sizeChoiceBox.valueProperty().addListener((observableValue, oldVal, newVal) -> {
			size = newVal;
			createPizza();
		});
		cheeseChoiseBox.valueProperty().addListener((observableValue, oldVal, newVal) -> {
			cheese = newVal;
			createPizza();
		});
	}

	// listens for changes in the quantity and adjusts the quantity and total cost
	// accordingly
	private void adjustQuantityListener() {
		quantitySlider.valueProperty().addListener((observableVal, oldVal, newVal) -> {
			quantity = newVal.intValue();
			quantityLabel.setText("" + newVal.intValue());
			totalCost.setText("$" + String.format("%.2f", calculateTotalCost()));
			if (quantity >= 10 && quantity <= 20) {
				discountLabel.setText("10% Discount Applied");
				discountLabel.setVisible(true);
			} else if (quantity > 20) {
				discountLabel.setText("15% Discount Applied");
				discountLabel.setVisible(true);
			} else {
				discountLabel.setVisible(false);
			}
		});
	}

	// handles event for ham checkbox, if ham is not selected, it will deselect
	// green pepper and pineapple, set the state to none, and also hide them from
	// visibility
	@FXML
	void handleHam(ActionEvent event) {
		if (hamCheckBox.isSelected()) {
			ham = "single";
			greenPepperCheckBox.setVisible(true);
			pineappleCheckBox.setVisible(true);
		} else {
			ham = "none";
			greenPepper = "none";
			pineapple = "none";
			greenPepperCheckBox.setSelected(false);
			pineappleCheckBox.setSelected(false);
			greenPepperCheckBox.setVisible(false);
			pineappleCheckBox.setVisible(false);
		}
		createPizza();
	}

	@FXML
	void handleGreenPepper(ActionEvent event) {
		if (greenPepperCheckBox.isSelected()) {
			greenPepper = "single";
		} else {
			greenPepper = "none";
		}
		createPizza();
	}

	@FXML
	void handlePineapple(ActionEvent event) {
		if (pineappleCheckBox.isSelected()) {
			pineapple = "single";
		} else {
			pineapple = "none";
		}
		createPizza();
	}

	// if the add line item button is clicked, it will create a new line item and
	// add text to the line item text areas
	@FXML
	void handleAddLineItem(ActionEvent event) {
		try {
			LineItem newItem = new LineItem(quantity, pizza);
			// since line items will not be deleted or edited, this method is more efficient
			// to calculate the total cost rather than making a function and looping through
			// an array list to sum the cost
			totalPizzasCost += newItem.getCost();
			lineItemTextArea.appendText(newItem.toString());
			lineItemTextArea.appendText("\n");
			lineItemCostTextArea.appendText("$" + String.format("%.2f", newItem.getCost()));
			lineItemCostTextArea.appendText("\n");
			lineItemsTotalCost.setText("$" + String.format("%.2f", totalPizzasCost));
		} catch (IllegalPizza e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}

	}

	// initializes choicebox values and creates a default pizza: small, single
	// cheese, ham
	private void initializePizzaValues() {
		sizeChoiceBox.setItems(FXCollections.observableArrayList("Small", "Medium", "Large"));
		sizeChoiceBox.setValue("Small");
		size = sizeChoiceBox.getValue();

		cheeseChoiseBox.setItems(FXCollections.observableArrayList("Single", "Double", "Triple"));
		cheeseChoiseBox.setValue("Single");
		cheese = cheeseChoiseBox.getValue();
		hamCheckBox.setSelected(true);
		if (hamCheckBox.isSelected()) {
			ham = "single";
		}
		pineapple = "none";
		greenPepper = "none";
		quantity = 1;
		createPizza();
	}

	// returns the total cost of the pizzas, a discount is applied if over 10 pizzas
	// is purchased
	private double calculateTotalCost() {
		if (quantity >= 10 && quantity <= 20) {
			return 0.90 * quantity * pizza.getCost();
		}
		if (quantity > 20) {
			return 0.85 * quantity * pizza.getCost();
		} else {
			return quantity * pizza.getCost();
		}
	}

	@FXML
	void initialize() {
		assert sizeChoiceBox != null : "fx:id=\"sizeChoiceBox\" was not injected: check your FXML file 'PizzaFXML.fxml'.";
		assert cheeseChoiseBox != null : "fx:id=\"cheeseChoiseBox\" was not injected: check your FXML file 'PizzaFXML.fxml'.";
		assert hamCheckBox != null : "fx:id=\"hamCheckBox\" was not injected: check your FXML file 'PizzaFXML.fxml'.";
		assert pineappleCheckBox != null : "fx:id=\"pineappleCheckBox\" was not injected: check your FXML file 'PizzaFXML.fxml'.";
		assert greenPepperCheckBox != null : "fx:id=\"greenPepperCheckBox\" was not injected: check your FXML file 'PizzaFXML.fxml'.";
		assert costPerPizza != null : "fx:id=\"costPerPizza\" was not injected: check your FXML file 'PizzaFXML.fxml'.";
		assert quantitySlider != null : "fx:id=\"quantitySlider\" was not injected: check your FXML file 'PizzaFXML.fxml'.";
		assert quantityLabel != null : "fx:id=\"quantityLabel\" was not injected: check your FXML file 'PizzaFXML.fxml'.";
		assert totalCost != null : "fx:id=\"totalCost\" was not injected: check your FXML file 'PizzaFXML.fxml'.";
		assert addLineItemButton != null : "fx:id=\"addLineItemButton\" was not injected: check your FXML file 'PizzaFXML.fxml'.";
		assert lineItemsTotalCost != null : "fx:id=\"lineItemsTotalCost\" was not injected: check your FXML file 'PizzaFXML.fxml'.";

		initializePizzaValues();
		choiceBoxListeners();
		adjustQuantityListener();

	}
}
