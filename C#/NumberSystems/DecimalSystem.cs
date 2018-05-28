/*  
    Created by Timothy Garrett
    5/27/2018
 */
 using System;
 using System.Collections.Generic;

 class DecimalSystem{
    private static List<string> addToList(string number, int size, List<string> outputList)
    {
        // We simply add a -1 to denote there was an exception.
        if(number.Equals("-1")){
            outputList.Add("-1");
            return outputList;
        }
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
    public static List<string> compute(List<string> list){
        List<string> newList = new List<string>();
        for(int i = 0; i < list.Count; i++)
            newList = addToList(list[i], list[i].Length, newList);
        return newList;
    }
 }