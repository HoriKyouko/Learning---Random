/*  
    Created by Timothy Garrett
    5/27/2018
*/
using System;
using System.IO;
using System.Collections.Generic;
/*
Takes in two arguments that are the .txt files and will output the specified base number system
layout. ie. 2047 will look like 2*10^3 + 0*10^2 + 4*10^1 + 7*10^0 or if it was with a decimal:
12.3 will look like 1*10^1 + 2*10^0 + 3*10^-1
*/
class NumberSystems
{
    /*
        What I want is to be able to pass in the input and output.txt as string args.
        This will be handed off to a method that will be capable of creating out streamReaders.
        I feel like this might be slightly redundant, but we'll see.
    */
    static void Main(string[] args)
    {
        List<string> list = FileHandler.ReadFile(args[0]); 
        DecimalSystem DecimalSystem = new DecimalSystem();
        list = DecimalSystem.compute(list);
        FileHandler.WriteFile(list, args[1]);
    }   
}
