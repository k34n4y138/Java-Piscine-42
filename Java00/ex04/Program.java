import java.util.Scanner;
import java.util.HashMap;


public class main
{
	// max char is 65535
	private static int[] _map = new int[65535]; // count of occurences of each char
	private static char[] order = new char[11]; // ascii code of top 10 occuring chars in descending order, last idx is to be outcased
	private static int _count = 0; // count of chars in the order array

	private static int _insert(char c) // inser in order array and return inserion index, if outcast idx is used count isn't incremented
	{
		for (int i = 0; i < _count; i++)
		{
			if (order[i] == c)
				return (i);
		}
		int ipos = _count;
		order[ipos] = c;
		_count += _count < 10 ? 1 : 0;
		// return index of the
		return (ipos);
	}
	private static void organize(char c, int occur)
	{
		int idx = _insert(c);
		for (int i = idx; i > 0; i--)
		{
			if (_map[order[i - 1]] < occur)
			{// occurence compare
				order[i] = order[i - 1];
				order[i - 1] = c;
			}
			else if (_map[order[i - 1]] == occur && order[i - 1] > c)
			{ // ascii compare
				order[i] = order[i - 1];
				order[i - 1] = c;
			}
			else
				break;
		}
	}

	private static void printBars()
	{
		char barchar = '#';
		int  barchar_val = 1 + _map[order[0]] / 10;
		for (int i = 0; i < _count; i++)
		{
			System.out.print(order[i] + ": ");
			for (int j = 0; j < _map[order[i]] / barchar_val; j++)
				System.out.print(barchar);
			System.out.println( _map[order[i]]);
		}
	}
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine())
		{
			char[] line = sc.nextLine().toCharArray();
			for (int i = 0; i < line.length; i++)
			{
				_map[line[i]]++;
				organize(line[i], _map[line[i]]);
			}
		}
		printBars();
	}
}