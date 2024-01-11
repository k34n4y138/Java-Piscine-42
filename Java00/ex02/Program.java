/*
 * Java00 Exercise 01
 * Author: zmoumen <zmoumen@student.1337.ma>
 */

/*
    * each line is a number
    * if the sum of the digits is prime, it means the number is the arbitrary query
 */

import java.util.Scanner;
import java.lang.Math;

public class Program {

    private static int sumOfDigits(int n) {
        int sum = 0;
        while (n > 0) {
            sum += (n % 10);
            n /= 10;
        }

        return sum;
    }
    private static boolean isPrime(int number) {
        if (number <= 1)
            return (false);
        for (int i = 2; i * i <= number; i++)
            if (number % i== 0)
                return (false);
        return (true);
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int queries = 0;
        while (input.hasNextInt()){
            if (isPrime(sumOfDigits(input.nextInt())))
                queries++;
        }
        System.out.println("Count of coffe-request - " + queries);
    }
}