package logic.numberHandler;

import java.math.BigInteger;

public class IntegerHandler {

    public static boolean checkInt(String str) {
        try {
            return Integer.parseInt(str) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkSimple(String str) {
        try {
            BigInteger a = new BigInteger(str);
            return a.isProbablePrime((int) Math.log(Integer.parseInt(str)));
        } catch (NullPointerException e) {
            return false;
        }
    }
}