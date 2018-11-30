// change package to whatever file directory your project runs on
package application;

import java.io.Serializable;

/**
 * A Class that describes the single line of a pizza order which includes the
 * Pizza Object and the amount of that pizza
 * 
 * * @author Tommy Poon CISC 124 NETID: 14tchp
 */
public class LineItem implements Comparable<LineItem>, Serializable {

    private static final long serialVersionUID = 4125966426358329466L;

    private Pizza pizzaPtr;
    private int numOfPizzas;

    /**
     * Full parameter constructor.
     * 
     * @param amount The amount of the same kind of pizza
     * @param pizza  The Pizza Object
     * @throws IllegalPizza If arguments are not legal.
     */
    // 2 parameter constructor invokes mutators
    public LineItem(int amount, Pizza pizza) throws IllegalPizza {
        if (pizza == null) {
            throw new IllegalPizza("Illegal pizza: you entered null");
        }
        setNumber(amount);
        setPizza(pizza);
    }

    /**
     * The default constructor is no amount of pizza is provided. It defaults to 1
     * pizza added.
     * 
     * @param pizza The Pizza Object
     */
    public LineItem(Pizza pizza) throws IllegalPizza {
        if (pizza == null) {
            throw new IllegalPizza("Illegal pizza: you entered null");
        }
        setNumber(1);
        setPizza(pizza);
    }

    /**
     * Sets the number of pizzas for the line item
     * 
     * @param numOfPizzas The number of pizzas can be between 1 and 100 inclusive
     * @throws IllegalPizza if the number of pizzas is out of bounds
     */
    public void setNumber(int numOfPizzas) throws IllegalPizza {
        if (numOfPizzas <= 0 || numOfPizzas > 100) {
            throw new IllegalPizza("You entered an illegal number of pizzas. Must be between 1 and 100 inclusive.");
        }
        this.numOfPizzas = numOfPizzas;
    }

    /*
     * Sets the Pizza object that will be in the LineItem. This is a private method
     * and makes the pizza immutable from the outside as they will not be able to
     * change the LineItem to point to another pizza
     */
    private void setPizza(Pizza pizza) {
        this.pizzaPtr = pizza;
    }

    /**
     * Returns the pizza that the line item is pointing to
     * 
     * @return The pizza object that the line item is pointing to
     */
    public Pizza getPizza() {
        return pizzaPtr;
    }

    /**
     * Returns the number of pizzas in the line item
     * 
     * @return The number of pizzas in the line item.
     */
    public int getNumber() {
        return numOfPizzas;
    }

    /**
     * Returns the total dollar amount of all the pizzas in the line item.
     * 
     * @return the total dollar amount of the pizza multiplied by the amount of
     *         pizzas. if there are 10 to 20 pizzas inclusive there is a 10%
     *         discount, if there are more than 20 pizzas the total gets a 15%
     *         discount
     */
    public double getCost() {
        if (numOfPizzas >= 10 && numOfPizzas <= 20) {
            return 0.90 * numOfPizzas * pizzaPtr.getCost();
        }
        if (numOfPizzas > 20) {
            return 0.85 * numOfPizzas * pizzaPtr.getCost();
        } else {
            return numOfPizzas * pizzaPtr.getCost();
        }
    }

    /**
     * A String representation of the current object. If the number of pizzas is
     * less than 10, it prefixes with a space so the string's line up when displayed
     * 
     * @return A String representation of the contents of the object containing the
     *         values of all the attributes.
     */
    // Overrides (replaces) the toString method of the Object class.
    @Override
    public String toString() {
        if (numOfPizzas < 10) {
            return " " + numOfPizzas + " " + pizzaPtr.toString();
        } else {
            return numOfPizzas + " " + pizzaPtr.toString();
        }

    }

    /**
     * Compares LineItem objects on the basis of the their cost. Highest cost will
     * be listed first
     * 
     * @param otherLineItem The other LineItem object.
     * @return A negative <code>int</code> if the current LineItem is more expensive
     *         than the otherLineItem, zero if their difference is less than a
     *         dollar and a positive number if the current LineItem costs less than
     *         the otherLineItem.
     */
    @Override
    public int compareTo(LineItem otherLineItem) {
        double difference = getCost() - otherLineItem.getCost();
        // if the difference is between -1.0 and 1.0 then it is within a dollar
        if (difference >= -1.0 && difference <= 1.0) {
            return 0;
        }
        // list the highest total cost first, so return -1
        if (difference > 1.0) {
            return -1;
        } else {
            return 1;
        }
    }

}