/*
    Created by Timothy Garrett
    5/28/18
 */
using System;
using System.Collections.Generic;

class HexSystem{
    private static List<string> addToListDecimalVariant(string number, int size, List<string> outputList)
    {
        // We simply add a -1 to denote there was an exception.
        if(number.Equals("-1")){
            outputList.Add("-1");
            return outputList;
        }
        string output = "";
        int temp = size-1;
        for(int i = 0; i < size; i++){
                if(i == (size-1))
                    output = String.Concat(output, number[i] + "*16^" + temp);
                else
                    output = String.Concat(output, number[i] + "*16^" + temp + " + ");
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
                    output = String.Concat(output, "0000");
                    break;
                case '1':
                    output = String.Concat(output, "0001");
                    break;
                case '2':
                    output = String.Concat(output, "0010");
                    break;
                case '3':
                    output = String.Concat(output, "0011");
                    break;
                case '4':
                    output = String.Concat(output, "0100");
                    break;
                case '5':
                    output = String.Concat(output, "0101");
                    break;
                case '6':
                    output = String.Concat(output, "0110");
                    break;
                case '7':
                    output = String.Concat(output, "0111");
                    break;
                case '8':
                    output = String.Concat(output, "1000");
                    break;
                case '9':
                    output = String.Concat(output, "1001");
                    break;
                case 'A':
                case 'a':
                    output = String.Concat(output, "1010");
                    break;
                case 'B':
                case 'b':
                    output = String.Concat(output, "1011");
                    break;
                case 'C':
                case 'c':
                    output = String.Concat(output, "1100");
                    break;
                case 'D':
                case 'd':
                    output = String.Concat(output, "1101");
                    break;
                case 'E':
                case 'e':
                    output = String.Concat(output, "1110");
                    break;
                case 'F':
                case 'f':
                    output = String.Concat(output, "1111");
                    break;
                default:
                    Console.Error.WriteLine("Invalid Hex Number");
                    outputList.RemoveAt(outputList.Count-1);
                    valid = false;
                    break;
            }
            if(i != 0 && valid)
                output = output.Insert((output.Length-1)-3, " ");
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