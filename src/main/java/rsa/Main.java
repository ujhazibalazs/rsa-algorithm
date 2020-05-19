package rsa;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        BigInteger p = GenerateKey.generateOddNumber(GenerateKey.minNum, GenerateKey.maxNum);
        while (!GenerateKey.MRPrimeTest(p)) {
            p = GenerateKey.generateOddNumber(GenerateKey.minNum, GenerateKey.maxNum);
        }
        System.out.println("p: " + p);

        BigInteger q = GenerateKey.generateOddNumber(GenerateKey.minNum, GenerateKey.maxNum);
        while (!GenerateKey.MRPrimeTest(q) || p.equals(q)) {
            q = GenerateKey.generateOddNumber(GenerateKey.minNum, GenerateKey.maxNum);
        }
        System.out.println("q: " + q);

        BigInteger n = p.multiply(q);
        System.out.println("n: " + n);

        BigInteger phin = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        System.out.println("phi n: " + phin);

        //BigInteger e = GenerateKey.generateOddNumber(new BigInteger("2"), phin);
        BigInteger e = GenerateKey.generateOddNumber(new BigInteger("2"), new BigInteger("50"));
        while (!e.gcd(phin).equals(BigInteger.ONE)) {
            //e = GenerateKey.generateOddNumber(new BigInteger("2"), phin);
            e = GenerateKey.generateOddNumber(new BigInteger("2"), new BigInteger("50"));
        }
        System.out.println("e: " + e);

        BigInteger d = GenerateKey.generateD(phin, e);
        System.out.println("d: " + d);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Give me a message: (msg < " + n + ")");
        BigInteger msg = scanner.nextBigInteger();
        while(msg.compareTo(n) >= 0 || msg.compareTo(BigInteger.ONE) <= 0) {
            System.out.println("Give me a message: (msg < " + n + ")");
            msg = scanner.nextBigInteger();
        }

        //ENCRYPT
        System.out.println("Message to encrypt: " + msg);
        BigInteger c = msg.modPow(e, n);
        System.out.println("Encrypted message: " + c);

        //DECRYPT
        System.out.println("Decrypted message: " + c.modPow(d, n));

    }
}
