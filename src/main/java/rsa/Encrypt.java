package rsa;

public class Encrypt {

    public static int encryptMessage(Key key, int message) {
        if (message < key.getN()) {
            return (int)Math.pow(message, key.getE()) % key.getN();
        }
        System.out.println("ERROR: Message is greater than N");
        return 1;
    }

}
