/*  
    Created by Timothy Garrett
    5/27/2018
*/
using System;
using System.IO;
using System.Collections.Generic;
/*
    Takes in two arguments that are the .txt files and will output the specified base 
    number system layout.
    
    If dealing with a base 10 number system 2047 will look like
    2*10^3 + 0*10^2 + 4*10^1 + 7*10^0 in Decimal.
    
    If it was with a decimal number: 12.3 will look like
    1*10^1 + 2*10^0 + 3*10^-1 in Decimal.

    If dealing with a base 2 number system 1010 will look like
    1*2^3 + 0*2^2 + 1*2^1 + 0*2^0 in Binary.

    If dealing with a base 8 number system we can convert it to either binary or decimal.
    For Binary we get 75 octal to look like 111 101 in Binary.
    For Decimal we get 75 octal to look like 7*8^1 + 5*8^0 in Decimal.

    If dealing with a base 16 number system we can convert it to either binary or decimal.
    For Binary we get A5 hex to look like 1010 0101 in Binary.
    For Decimal we get A5 hex to look like A*16^1 + 5*16^0 in Decimal.
*/
class NumberSystems
{
    static void Main(string[] args)
    {
        List<string> list = FileHandler.ReadFile(args[0]); 
        FileHandler.WriteFile(DecimalSystem.compute(list), args[1]);
    }   
}
