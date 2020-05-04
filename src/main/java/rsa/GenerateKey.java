package rsa;

import java.util.*;

public class GenerateKey {
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

    private static int getRandom(int max) {
        Random rand = new Random();
        return rand.nextInt(max);
    }


    public int generatePrime(int min, int max) {
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if(isPrime(i)) {
                primes.add(i);
            }
        }
        return primes.get(getRandom(primes.size()));
    }
}
