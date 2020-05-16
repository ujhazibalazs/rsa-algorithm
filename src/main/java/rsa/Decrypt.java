package rsa;

public class Decrypt {

    public static int decryptMessage(Key key, int cipherText) {
        return (int)Math.pow(cipherText, key.getD()) % key.getN();
    }

}
