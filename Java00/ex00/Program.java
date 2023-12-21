/**
 * Java00 Exercise 00
 * Author: zmoumen <zmoumen@student.1337.ma>
 */

// package Java00.ex00;

public class Program {
	public static void main(String[] args) {
		int sum = 0;
		for (int number = 1337;				/*subject specifies changing this number */
			number > 0; number /= 10) {
			sum += number % 10;
	}
	System.out.println(sum);
	}
}