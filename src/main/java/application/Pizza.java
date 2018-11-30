// change package to whatever file directory your project runs on
package application;

import java.io.Serializable;

/**
 * A Class to store information about a pizza
 * 
 * @author Tommy Poon CISC 124 NETID: 14tchp
 */
public class Pizza implements Serializable {

    private static final long serialVersionUID = 4125965356358329466L;

    private String size;
    private String cheese;
    private String pineapple;
    private String greenPepper;
    private String ham;

    /**
     * Full parameter constructor.
     * 
     * @param sz        The size of the pizza
     * @param chse      The Amount of Cheese on the pizza
     * @param pineapp   The amount of pineapple on the pizza
     * @param grnPepper The amount of green pepper on the pizza.
     * @param hm        The amount of ham on the pizza.
     * @throws IllegalPizza If arguments are not legal.
     */
    // 5 parameter constructor invokes mutators
    public Pizza(String sz, String chse, String pineapp, String grnPepper, String hm) throws IllegalPizza {
        if (sz == null || chse == null || pineapp == null || grnPepper == null || hm == null) {
            throw new IllegalPizza("Illegal pizza: you entered null");
        }

        sz = sz.toLowerCase();
        chse = chse.toLowerCase();
        pineapp = pineapp.toLowerCase();
        grnPepper = grnPepper.toLowerCase();
        hm = hm.toLowerCase();

        setSize(sz);
        setCheese(chse);
        setHam(hm);
        setPineapple(pineapp);
        setGreenPepper(grnPepper);

    }

    /**
     * The default constructor is no parameters are applied creates a small pizza
     * with single cheese and single ham
     * 
     */
    public Pizza() {
        size = "small";
        cheese = "single";
        pineapple = "none";
        greenPepper = "none";
        ham = "single";
    }

    /**
     * Sets the size of the pizza.
     * 
     * @param size The size of the pizza can be "small", "medium", or "large"
     * @throws IllegalPizza if the size isn't "small", "medium", or "large"
     */
    public void setSize(String size) throws IllegalPizza {
        if (size.equals("small") || size.equals("medium") || size.equals("large")) {
            this.size = size;
        } else {
            throw new IllegalPizza("Illegal size: " + size);
        }
    }

    /**
     * Sets the cheese topping of the pizza.
     * 
     * @param cheese The amount of cheese can be "single", "double", or "triple"
     * @throws IllegalPizza if the cheese isn't "single", "double", or "triple"
     */
    public void setCheese(String cheese) throws IllegalPizza {
        if (cheese.equals("single") || cheese.equals("double") || cheese.equals("triple")) {
            this.cheese = cheese;
        } else {
            throw new IllegalPizza("Illegal cheese: " + cheese);
        }
    }

    /**
     * Sets the ham topping of the pizza.
     * 
     * @param ham The amount of ham can be "none", or "single".
     * @throws IllegalPizza if the ham isn't "none", or "single".
     */
    public void setHam(String ham) throws IllegalPizza {
        if (ham.equals("none") || ham.equals("single")) {
            this.ham = ham;
        } else {
            throw new IllegalPizza("Illegal ham: " + ham);
        }
    }

    /**
     * Sets the pineapple topping of the pizza. Must also have ham set to "single"
     * 
     * @param pineapple The amount of pineapple can be "none", or "single".
     * @throws IllegalPizza if the pineapple isn't "none", or "single". Or if
     *                      pineapple is "single" and ham is "none"
     */
    public void setPineapple(String pineapple) throws IllegalPizza {
        if (pineapple.equals("none") || pineapple.equals("single")) {
            if (this.ham.equals("single") && pineapple.equals("single")) {
                this.pineapple = pineapple;
            }
            if (pineapple.equals("none")) {
                this.pineapple = pineapple;
            }
            if (pineapple.equals("single") && ham.equals("none")) {
                throw new IllegalPizza("Illegal pizza, must have ham to have pineapple");
            }
        } else {
            throw new IllegalPizza("Illegal pineapple: " + pineapple);
        }
    }

    /**
     * Sets the green pepper topping of the pizza. Must also have ham set to
     * "single"
     * 
     * @param greenPepper The amount of greenPepper can be "none", or "single".
     * @throws IllegalPizza if the greenPepper isn't "none", or "single". Or if
     *                      greenPepper is "single" and ham is "none"
     */
    public void setGreenPepper(String greenPepper) throws IllegalPizza {
        if (greenPepper.equals("none") || greenPepper.equals("single")) {
            if (this.ham.equals("single") && greenPepper.equals("single")) {
                this.greenPepper = greenPepper;
            }
            if (greenPepper.equals("none")) {
                this.greenPepper = greenPepper;
            }
            if (greenPepper.equals("single") && ham.equals("none")) {
                throw new IllegalPizza("Illegal pizza, must have ham to have green pepper");
            }
        } else {
            throw new IllegalPizza("Illegal green pepper: " + greenPepper);
        }
    }

    /**
     * Returns the cost of the pizza.
     * 
     * @return The cost of the pizza in dollars
     */
    public double getCost() {
        double cost = 0;
        switch (size) {
        case "small":
            cost += 7.00;
            break;
        case "medium":
            cost += 9.00;
            break;
        case "large":
            cost += 11.00;
            break;
        default:
            break;
        }

        switch (cheese) {
        case "single":
            cost += 0;
            break;
        case "double":
            cost += 1.50;
            break;
        case "triple":
            cost += 3.00;
            break;
        default:
            break;
        }
        if (pineapple.equals("single")) {
            cost += 1.50;
        }
        if (greenPepper.equals("single")) {
            cost += 1.50;
        }
        if (ham.equals("single")) {
            cost += 1.50;
        }
        return cost;
    }

    /**
     * A String representation of the current object.
     * 
     * @return A String representation of the contents of the object containing the
     *         values of all the attributes.
     */
    // Overrides (replaces) the toString method of the Object class.
    @Override
    public String toString() {
        String str = "";
        str += size + " pizza, " + cheese + " cheese";
        if (pineapple.equals("single")) {
            str += ", pineapple";
        }
        if (greenPepper.equals("single")) {
            str += ", green pepper";
        }
        if (ham.equals("single")) {
            str += ", ham";
        }
        // str += ". Cost: $" + String.format("%.2f", getCost()) + " each.";

        return str;
    }

    /**
     * Tests two Pizza objects for equality.
     * 
     * @return <code>true</code> if all the attributes of both objects are exactly
     *         equal, <code>false</code> otherwise.
     * @param otherPizza The other Pizza object.
     */
    // Overrides the equals method of the Object class.
    @Override
    public boolean equals(Object otherPizza) {
        if (otherPizza instanceof Pizza) {
            Pizza otherP = (Pizza) otherPizza;
            if (size.equals(otherP.size) && cheese.equals(otherP.cheese) && pineapple.equals(otherP.pineapple)
                    && greenPepper.equals(otherP.greenPepper) && ham.equals(otherP.ham)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a copy of the of the current Pizza object.
     * 
     * @return A copy of the current object.
     */
    // Overrides the clone method in the Object class.
    @Override
    public Pizza clone() {
        Pizza pizzaCopy = null;
        try {
            pizzaCopy = new Pizza(size, cheese, pineapple, greenPepper, ham);
        } catch (IllegalPizza e) {
            // Should never get here!
            return null;
        } // end try/catch
        return pizzaCopy;
    }

}