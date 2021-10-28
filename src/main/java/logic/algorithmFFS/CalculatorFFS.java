package logic.algorithmFFS;

import java.math.BigInteger;

public class CalculatorFFS {

    private static BigInteger n;


    public static String calcV(String s) {

        return new BigInteger(s).pow(2).mod(n).toString();
    }

    public static String calcX(String r) {

        return new BigInteger(r).pow(2).mod(n).toString();
    }

    public static String calcY(String r, String s, String e) {

        return new BigInteger(r).multiply(new BigInteger(s).pow(Integer.parseInt(e))).mod(n).toString();
    }

    public static String calcXRes(String x, String v, String e) {

        return new BigInteger(x).multiply(new BigInteger(v).pow(Integer.parseInt(e))).mod(n).toString();
    }

    public static String calcYy(String y) {

        return new BigInteger(y).pow(2).mod(n).toString();
    }

    public static String getN() {
        return n.toString();
    }

    public static void setN(String n) {
        CalculatorFFS.n = new BigInteger(n);
    }
}
