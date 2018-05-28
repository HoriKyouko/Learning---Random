/*
    Created by Timothy Garrett
    5/28/18
 */
using System;
using System.Collections.Generic;

class OctalSystem{
    private static List<string> addToListDecimalVariant(string number, int size, List<string> outputList){
        // We simply add a -1 to denote there was an exception.
        if(number.Equals("-1")){
            outputList.Add("-1");
            return outputList;
        }
        string output = "";
        int temp = size-1;
        for(int i = 0; i < size; i++){
                if(i == (size-1))
                    output = String.Concat(output, number[i] + "*8^" + temp);
                else
                    output = String.Concat(output, number[i] + "*8^" + temp + " + ");
                temp--;
            }
        outputList.Add(output);
        return outputList;
    }

    private static List<string> addToListBinaryVariant(string v, int length, List<string> outputList)
    {
        if(v.Equals("-1")){
            outputList.Add("-1");
            return outputList;
        }
        string output = "";
        for(int i = 0; i < length; i++){
            bool valid = true;
            switch(v[i]){
                case '0':
                    output = String.Concat(output, "000");
                    break;
                case '1':
                    output = String.Concat(output, "001");
                    break;
                case '2':
                    output = String.Concat(output, "010");
                    break;
                case '3':
                    output = String.Concat(output, "011");
                    break;
                case '4':
                    output = String.Concat(output, "100");
                    break;
                case '5':
                    output = String.Concat(output, "101");
                    break;
                case '6':
                    output = String.Concat(output, "110");
                    break;
                case '7':
                    output = String.Concat(output, "111");
                    break;
                default:
                    Console.Error.WriteLine("Invalid Octal Number");
                    outputList.RemoveAt(outputList.Count-1);
                    valid = false;
                    break;
            }
            if(i != 0 && valid)
                output = output.Insert((output.Length-1)-2, " ");
        }
        outputList.Add(output);
        return outputList;
    }
    public static List<string> compute(List<string> list, bool decVariant){
        List<string> newList = new List<string>();
        for(int i = 0; i < list.Count; i++){
            if(decVariant)
                newList = addToListDecimalVariant(list[i], list[i].Length, newList);
            else
                newList = addToListBinaryVariant(list[i], list[i].Length, newList);
        }
            
        return newList;
    }
}