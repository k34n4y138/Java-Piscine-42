/*
 * Java00 Exercise 01
 * Author: zmoumen <zmoumen@student.1337.ma>
 */

import java.util.Scanner;
import java.lang.Math;

public class Program {
    public static void main(String[] args) {
        int input = 0;
        int iters = 0;
        Scanner scnr = new Scanner(System.in);

        try {
            input = scnr.nextInt();
            iters = isPrime(input);
            System.out.println((iters > 0) + " " + Math.abs(iters));
        } catch (IllegalArgumentException e) {
            System.err.println("Illegal Argument");
        } catch (Exception e) {
            System.err.println("Error parsing input");
        }
    }

    private static int isPrime(int number) throws Exception { /* Negative if not prime */
        if (number <= 1) {
            throw new IllegalArgumentException("Illegal");
        }
        int iters = 1;
        for (int i = 2; i * i <= number; i++, iters++)
            if (number % i== 0)
                return (-1 * iters);
        return (iters);
    }
}