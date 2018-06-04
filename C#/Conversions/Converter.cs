/*
    Created by Timothy Garrett
    5/30/18
 */
using System;
using System.Collections.Generic;

public enum ConversionOptions{
    bin = 1, dec, hex,
}

class Program
{
    static void Main(string[] args)
    {
        List<string> list = FileHandler.ReadFile(args[0]);
        FileHandler.WriteFile(HexConversion.compute(list, (int)ConversionOptions.dec),args[1]);
    }
}
