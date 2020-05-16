package rsa;

public class Main {
    public static void main(String[] args) {

        Key key1 = GenerateKey.generateKey();
        System.out.println("e: " + key1.getE());
        System.out.println("d: " + key1.getD());
        System.out.println("n: " + key1.getN());

        //Key key2 = GenerateKey.generateKey();
        //System.out.println("e: " + key2.getE());
        //System.out.println("d: " + key2.getD());

        Encrypt enc = new Encrypt();
        int message = 123;
        int cipherText = enc.encryptMessage(key1, message);
        System.out.println("Encripted message: " + cipherText);

        Decrypt dec = new Decrypt();
        System.out.println("Decripted message: " + dec.decryptMessage(key1, cipherText));

    }
}
