// change package to whatever file directory your project runs on
package application;

/**
 * A class which extends Exception. The error is thrown when someone tries to
 * create an invalid Pizza or invalid LineItem
 * 
 * * @author Tommy Poon CISC 124 NETID: 14tchp
 */
public class IllegalPizza extends Exception {

    private static final long serialVersionUID = 4125965356358325431L;

    public IllegalPizza(String message) {
        super(message);
    }
}