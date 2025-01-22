package unit08;

import java.util.ArrayList;
import java.util.List;

public class Greedy {
    public static List<Currency> makeChange (double price, double payment) {
        // Create coin roll
        List<Currency> coinRoll = new ArrayList<> ();
        Currency [] currencies = Currency.values ();
        // change = payment - price
        double change = payment - price;
        // for every currency value:
        for (int index = currencies.length - 1; index > -1; index--) {
            Currency currency = currencies [index];
        //   calculate largest whole number of currency that is less than change
            int number = (int) (change / currency.getValue () + 0.005);
        //   add number currency values to coin roll
            for (int i = 0; i < number; i++) {
                coinRoll.add (currency);
            }
        //   subtract number * currency value from change 
            change -= number * currency.getValue ();
        }
        // Return coin roll
        return coinRoll;
    }

    public static void main(String[] args) {
        System.out.println (makeChange (4.37, 10));
    }
}