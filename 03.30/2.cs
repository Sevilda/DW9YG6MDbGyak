using System;
using System.Linq;
using System.Collections.Generic;

public class Program
{
	public static void Main()
	{
		//a)
		List<int> numbers = new List<int>(){0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		//b)
		numbers.Where(x=> x%2==0).Select(x=>x*x).Dump();
		//c, avg)
		numbers.Average().Dump();
		//d, avg diff
		numbers.Where(x=>x > numbers.Average()).Select(x=> Math.Abs(x-numbers.Average())).Dump();
		//e diff to num
		numbers.GroupBy(x => Math.Abs(x - numbers.Average())).Dump();
	}
}