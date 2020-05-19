package rsa;

import java.util.*;
import java.math.BigInteger;

public class GenerateKey {

    public static BigInteger minNum = new BigInteger("100");
    public static BigInteger maxNum = new BigInteger("256");

    private static BigInteger randomBigInteger(BigInteger min, BigInteger max) {
        BigInteger num = max.subtract(min);
        Random rand = new Random();
        int len = max.bitLength();
        BigInteger res = new BigInteger(len, rand);
        if (res.compareTo(min) < 0) {
            res = res.add(min);
        }
        if (res.compareTo(num) >= 0) {
            res = res.mod(num).add(min);
            return res;
        }
        return BigInteger.ZERO;
    }

    public static BigInteger generateOddNumber(BigInteger min, BigInteger max) {
        BigInteger num = randomBigInteger(min, max);
        while (num.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            num = randomBigInteger(min, max);
        }
        return num;
    }

    public static boolean MRPrimeTest(BigInteger num) {

        if (num.compareTo(new BigInteger("3")) <= 0) {
            return false;
        }

        //Calculating S and d
        BigInteger d = num.subtract(BigInteger.ONE);
        BigInteger S = BigInteger.ZERO;
        while (d.mod(new BigInteger("2")).equals(BigInteger.ZERO)) {
            //System.out.println("d: " + d);
            d = d.divide(new BigInteger("2"));
            S = S.add(BigInteger.ONE);
        }
        //System.out.println("\nd: " + d);
        //System.out.println("S: " + S);

        //Korok szama
        BigInteger k = new BigInteger("3");
        //Bazis (1 < a < p)
        BigInteger a = new BigInteger("1");

        //k szor tesztelunk
        for (int j = 0; j < k.intValue(); j++) {
            //System.out.println("\n" + (j+1) + ". teszt:");
            a = a.add(BigInteger.ONE);
            //System.out.println("a: " + a);

            //a^d test
            if (a.modPow(d,num).equals(BigInteger.ONE)) {
                //System.out.println("A szám összetett.");
                return false;
            }

            //a^2^i*d test
            for (int i = 0; i <= S.intValue() ; i++) {
                if (a.modPow(BigInteger.TWO.pow(i).multiply(d),num).equals(num.subtract(BigInteger.ONE))) {
                    //System.out.println("Sikeres teszt.");
                    return true;
                }
            }
        }
        //System.out.println("Sikertelen teszt.");
        return false;
    }

    public static BigInteger generateD(BigInteger phi_n, BigInteger e) {
        BigInteger x = BigInteger.ZERO;
        //System.out.println("x: " + x);
        BigInteger ed = BigInteger.ZERO;
        BigInteger result = BigInteger.ZERO;
        do {
            ed = x.multiply(phi_n).add(BigInteger.ONE);
            //System.out.println("ed: " + ed);
            x = x.add(BigInteger.ONE);
            //System.out.println("x: " + x);
            result = ed.divide(e).multiply(e);
            //System.out.println("result: " + result);
        } while(!ed.equals(result));
        return ed.divide(e);
    }

}
