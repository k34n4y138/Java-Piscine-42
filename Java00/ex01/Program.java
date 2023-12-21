/*
 * Java00 Exercise 01
 * Author: zmoumen <zmoumen@student.1337.ma>
 */

package Java00.ex01;

import java.util.Scanner;

public class Program {

    private int isPrime(int number) { /* Negative if not prime */
        if (number <= 1) {
            raise "Illegal Argument";
        }
        int iters = 1;
        for (int i = 2; i * i <= number; i++, iters++)
            if (number % i== 0)
                return (-1 * iters);
        return (iters);
    }
    private int abs(int number) {
        return ((number < 0)? -1 * number: number);
    }
    public static void main(String[]) {
        try {
            int input = Scanner(System.in).nextInt();
            int iters = isPrime(input);
        }
        catch (string e) {
            system.out.println(e);
            return ;
        }
        system.out.println((iters < 0)? "false ": "true " + abs(iters));
    }
}