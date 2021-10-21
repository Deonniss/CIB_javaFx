package logic.algorithmSKey;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SKey {

    public static String md5(String str) {
        String hash = "undefined";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(str.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte item : bytes) {
                builder.append(String.format("%02x", item));
            }
            hash = builder.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
}
