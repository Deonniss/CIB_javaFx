package logic.diffiHelman;

import java.math.BigInteger;

public class DiffiHelman {

    public static int modularDivision(String divisible, String divisor, String power) {

        BigInteger result = new BigInteger(divisible);
        BigInteger divisorBI = new BigInteger(divisor);
        BigInteger powerBI = new BigInteger(power);

        return result.modPow(powerBI, divisorBI).intValue();
    }
}