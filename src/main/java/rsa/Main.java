package rsa;

public class Main {
    public static void main(String[] args) {
        GenerateKey gk = new GenerateKey();
        int prime = gk.generatePrime(1, 100);
        System.out.println(prime);
    }
}
