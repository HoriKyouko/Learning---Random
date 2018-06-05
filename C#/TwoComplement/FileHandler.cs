/*
    Created by Timothy Garrett
    5/28/18
 */
using System;
using System.IO;
using System.Collections.Generic;
using System.Text.RegularExpressions;

class FileHandler<T>{
    /*
        Simple function for reading from a file and adding it to a list and then returning
        that list. Only works if each entry is a single line.
     */
    public static List<string> ReadFile(string fileName){
        List<string> fileList = new List<string>();
        using(StreamReader sr = new StreamReader(fileName)){
            while(!sr.EndOfStream){
                string temp = sr.ReadLine();
                if(IsNumeric(temp))
                    fileList.Add(temp);
                else{
                    Console.Error.WriteLine("An exception was thrown check input file."); 
                    fileList.Add("-1");
                }
            }
        }
        return fileList;
    }
    /*
        Found on StackOverflow:
        (https://stackoverflow.com/questions/894263/how-do-i-identify-if-a-string-is-a-number)
        
        Minor changes made to the source code:
        -Allowed capital letters for Hex.
        -Removed the 0x and 0b for Hex and binary resepectively and changed it to x and b.

        Originally From PHP documentation for is_numeric
        (http://php.net/manual/en/function.is-numeric.php)
    
        Finds whether the given variable is numeric.

        Numeric strings consist of optional sign, any number of digits, optional decimal part and optional
        exponential part. Thus +0123.45e6 is a valid numeric value.

        Hexadecimal (e.g. 0xf4c3b00c), Binary (e.g. 0b10100111001), Octal (e.g. 0777) notation is allowed
        too but only without sign, decimal and exponential part.
    */
    private static readonly Regex _isNumericRegex = new Regex("^(" +
                /*Hex*/ @"x[0-9a-fA-F]+"   + "|" +
                /*Bin*/ @"b[01]+"          + "|" + 
                /*Oct*/ @"0[0-7]*"          + "|" +
                /*Dec*/ @"((?!0)|[-+]|(?=0+\.))(\d*\.)?\d+(e\d+)?" + 
                ")$" );
    private static bool IsNumeric(string temp)
    {
        return _isNumericRegex.IsMatch(temp);
    }

    /*
        Simple function for writing a given list to an output file. Doesn't return anything
        but each entry is on it's own line.
    */
    public static void WriteFile(List<T> list, string fileName){
        using(StreamWriter sw = new StreamWriter(fileName))
            for(int i = 0; i < list.Count; i++)
                sw.WriteLine(list[i]);
    }
}