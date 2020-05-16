package rsa;

import java.util.*;

public class GenerateKey {

    private static int min = 100;
    private static int max = 256;

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int getRandom(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public static int generatePrime(int min, int max) {
        ArrayList<Integer> primes = new ArrayList<Integer>();
        for (int i = min; i <= max; i++) {
            if(isPrime(i)) {
                primes.add(i);
            }
        }
        return primes.get(getRandom(primes.size()));
    }

    public static int gcd(int u, int v) {
        if (v > 0) {
            return gcd(v, u % v);
        }
        else if (v == 0) {
            return u;
        }
        else return 1;
    }

    public static int generateD(int phi_n, int e) {
        int x = 0;
        double d;
        do {
            d = ((double)1 + x * phi_n) / e;
            x++;
        } while (d % 1 != 0);
        return (int)d;
    }

    public static Key generateKey() {
        Key key = new Key();
        key.setP(generatePrime(min, max));
        do {
            key.setQ(generatePrime(min, max));
        } while (key.getP() == key.getQ());
        key.setN(key.getP() * key.getQ());
        key.setPhi_n((key.getP() - 1) * (key.getQ() - 1));
        do {
            key.setE(getRandom(key.getPhi_n() - 2) + 2);
        } while (gcd(key.getE(), key.getPhi_n()) != 1);
        key.setD(generateD(key.getPhi_n(), key.getE()));
        return key;
    }

}
