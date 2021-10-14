package logic.algorithmSKey;

public class IntegerHandlerSKey {

    public static boolean checkInt(String str) {
        try {
            return Integer.parseInt(str) > 1;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
