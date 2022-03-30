using System;
using System.Linq;
using System.Collections.Generic;


public static class Program
{
	public static void Main()
	{
		//a)
		var names = new[]{"Vadon Enikő", "Bolyki Balázs", "Toronya Bertalan", "Polonkai Dávid", "Fazekas Levente", "Német Viktor"};
		//b)
		Console.Write(names.Take(3).Select(x => x)).Dump();
		//c)
		names.OrderBy(x => x.Split()[0]).Dump();
		//d)
		names.Where(x => x.Contains("L") || x.Contains("l")).Count().Dump();
		//e)
		names.Where(x => x.Split(' ')[0].Length > 5).Select(x => x.Split(' ')[1]).Dump();		
	}


}

