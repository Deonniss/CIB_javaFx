package logic.algorithmDiffiHelman.numberHandler;

import java.math.BigInteger;
import java.util.Random;

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

    public static boolean checkBigInt(String str) {
        try {
            return str.matches("\\d+");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkSimpleBigInt(String str) {
        try {
            return new BigInteger(str).isProbablePrime(1);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String generateProbablyPrime(int bits) {
        return BigInteger.probablePrime(bits, new Random()).toString();
    }
}