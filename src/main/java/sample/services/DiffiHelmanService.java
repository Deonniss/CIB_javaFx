package sample.services;

import logic.diffiHelman.DiffiHelman;
import logic.numberHandler.IntegerHandler;

public class DiffiHelmanService {


    public static boolean checkValidationInteger(String text) {
        return IntegerHandler.checkInt(text);
    }

    public static boolean checkValidationSimple(String text) {
        return IntegerHandler.checkSimple(text);
    }

    public static int modularDivision(String divisible, String divisor, String power) {
        return DiffiHelman.modularDivision(divisible, divisor, power);
    }
}
