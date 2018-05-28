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
        List<string> list = ReadFile(args[0]);
        List<string> outputList = new List<string>();
        for(int i = 0; i < list.Count; i++){
            string number = list[i];
            int size = number.Length;
            outputList = addToList(number, size, outputList);
        }
        WriteFile(outputList, args[1]);
    }

    private static List<string> addToList(string number, int size, List<string> outputList)
    {
        string output = "";
        int temp = size-1;
        if(number.Contains(".")){
            output = addDecimalToList(number, size);
        }
        else{
            for(int i = 0; i < size; i++){
                if(i == (size-1))
                    output = String.Concat(output, number[i] + "*10^" + temp);
                else
                    output = String.Concat(output, number[i] + "*10^" + temp + " + ");
                temp--;
            }
        }

        outputList.Add(output);
        return outputList;
    }
    /*
        Need to work on handling when your decimal goes further than 1 place ie 165.875
     */
    private static string addDecimalToList(string number, int size)
    {
        string output = "";
        int temp = number.IndexOf('.') - 1;
        for(int i = 0; i < size; i++){
            if(i == (size-1)){
                output = String.Concat(output, number[i] + "*10^" + temp);
                temp--;
            }
            else if(number[i].Equals('.')){}
            else{
                output = String.Concat(output, number[i] + "*10^" + temp + " + ");
                temp--;
            }
        }
        return output;
    }

    /*
        Simple function for reading from a file and adding it to a list and then returning
        that list. Only works if each entry is a single line.
     */
    static List<string> ReadFile(string fileName){
        List<string> fileList = new List<string>();
        using(StreamReader sr = new StreamReader(fileName)){
            while(!sr.EndOfStream){
                fileList.Add(sr.ReadLine());
            }
        }
        return fileList;
    }
    /*
        Simple function for writing a given list to an output file. Doesn't return anything
        but each entry is on it's own line.
     */
    static void WriteFile(List<string> list, string fileName){
        using(StreamWriter sw = new StreamWriter(fileName))
            for(int i = 0; i < list.Count; i++)
                sw.WriteLine(list[i]);
    }
}
