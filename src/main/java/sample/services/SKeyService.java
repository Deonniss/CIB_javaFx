package sample.services;

import logic.algorithmSKey.IntegerHandlerSKey;
import logic.algorithmSKey.SKey;
import logic.algorithmSKey.SKeyServer;

import java.util.Arrays;
import java.util.List;

public class SKeyService {

    public static SKeyServer sKeyServer = new SKeyServer();

    public static String[] calculateHashes(String password, int n) {

        String[] arrayOfHashes = new String[n];
        arrayOfHashes[0] = SKey.md5(password);
        for (int i = 1; i < n; i++) {
            arrayOfHashes[i] = SKey.md5(arrayOfHashes[i - 1]);
        }

        sKeyServer.setActualValue(SKey.md5(arrayOfHashes[n - 1]));
        sKeyServer.setAllValues(Arrays.asList(arrayOfHashes));

        return arrayOfHashes;
    }

    public static boolean checkValidationInteger(String value) {

        return IntegerHandlerSKey.checkInt(value);
    }

    public static boolean checkValidationEquals(String value) {

        return SKey.md5(value).equalsIgnoreCase(sKeyServer.getActualValue());
    }

    public static void replaceActualValue(String value) {
        sKeyServer.setActualValue(value);
    }

    public static List<String> getAllElements() {
        return sKeyServer.getAllValues();
    }

    public static void setAllElements(List<String> list) {
        sKeyServer.setAllValues(list);
    }

    public static String getActualValue() {
        return sKeyServer.getActualValue();
    }
}
