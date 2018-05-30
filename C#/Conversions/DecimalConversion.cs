/*
    Created by Timothy Garrett
    5/30/18
 */
using System;
using System.Collections.Generic;

class DecimalConversion{
    public static List<string> compute(List<string> list, int choice){
        List<string> newList = new List<string>();
        for(int i = 0; i < list.Count; i++){
            if(choice == 1)
                newList = ConvertToBinary(list[i], newList);
            else if(choice == 3)
                newList = ConvertToHex(list[i],  newList);
        }
        return newList;
    }
    private static List<string> ConvertToHex(string v, List<string> newList)
    {
        if(v.Equals("-1")){
            newList.Add("-1");
            return newList;
        }
        List<string> temp = new List<string>();
        temp = ConvertToBinary(v, temp);
        string output = temp[0].ToString();
        int length = output.Length;
        if(length % 4 == 0){
            newList.Add(CreateHex(output, length));
        }
        else if(length % 4 == 1){
            output = String.Concat("000", output);
            length = output.Length;
            newList.Add(CreateHex(output, length));
        }
        else if(length % 4 == 2){
            output = String.Concat("00", output);
            length = output.Length;
            newList.Add(CreateHex(output, length));
        }
        else{
            output = String.Concat("0", output);
            length = output.Length;
            newList.Add(CreateHex(output, length));
        }
        return newList;
    }
    private static string CreateHex(string output, int length)
    {
        string newOutput = "";
        string temp = "";
        bool value = true;
        int j = 0, i = 0, section = length/4;
        while(j < section){
            if(i % 4 == 0 && i != 0 && value){
                switch(temp){
                    case "0000":
                        newOutput = String.Concat(newOutput, "0");
                        break;
                    case "0001":
                        newOutput = String.Concat(newOutput, "1");
                        break;
                    case "0010":
                        newOutput = String.Concat(newOutput, "2");
                        break;
                    case "0011":
                        newOutput = String.Concat(newOutput, "3");
                        break;
                    case "0100":
                        newOutput = String.Concat(newOutput, "4");
                        break;
                    case "0101":
                        newOutput = String.Concat(newOutput, "5");
                        break;
                    case "0110":
                        newOutput = String.Concat(newOutput, "6");
                        break;
                    case "0111":
                        newOutput = String.Concat(newOutput, "7");
                        break;
                    case "1000":
                        newOutput = String.Concat(newOutput, "8");
                        break;
                    case "1001":
                        newOutput = String.Concat(newOutput, "9");
                        break;
                    case "1010":
                        newOutput = String.Concat(newOutput, "A");
                        break;
                    case "1011":
                        newOutput = String.Concat(newOutput, "B");
                        break;
                    case "1100":
                        newOutput = String.Concat(newOutput, "C");
                        break;
                    case "1101":
                        newOutput = String.Concat(newOutput, "D");
                        break;
                    case "1110":
                        newOutput = String.Concat(newOutput, "E");
                        break;
                    case "1111":
                        newOutput = String.Concat(newOutput, "F");
                        break;
                    default:
                        Console.Error.WriteLine("Invalid Hex Number");
                        break;
                }
                temp = "";
                value = false;
                j++;
            }
            else{
                temp = String.Concat(temp, output[i]);
                value = true;
                i++;
            }
        }
        return newOutput;
    }

    private static List<string> ConvertToBinary(string v, List<string> newList)
    {
        if(v.Equals("-1")){
            newList.Add("-1");
            return newList;
        }
        string output = "";
        int value = Int32.Parse(v);
        bool notFinished = true;
        while(notFinished){
            int quotient = value / 2;
            int remainder  = value % 2;
            output = String.Concat(remainder.ToString(), output);
            if(quotient == 0)
                notFinished = false;
            value = quotient;
        }
        newList.Add(output);
        return newList;
    }
}