/*
    Created by Timothy Garrett
    5/30/18
 */
using System;
using System.Collections.Generic;

class HexConversion{
    public static List<string> compute(List<string> list, int choice){
        List<string> newList = new List<string>();
        for(int i = 0; i < list.Count; i++){
            if(choice == 2)
                newList = ConvertToDecimal(list[i], newList);
            else if(choice == 1)
                newList = ConvertToBinary(list[i],  newList);
        }
        return newList;
    }

    private static List<string> ConvertToBinary(string v, List<string> newList)
    {
        if(v.Equals("-1")){
            newList.Add("-1");
            return newList;
        }
        string output = "";
        for(int i = 1; i < v.Length; i++){
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
                    break;
            }
        }
        output = RemoveTrailingZeros(output);
        newList.Add(output);
        return newList;
    }

    private static string RemoveTrailingZeros(string output)
    {
        if(output.Equals("0000"))
            return "0";
        int i = 0;
        while(output[i] != '1'){
            output = output.Remove(i,1);
        }
        return output;   
    }

    private static List<string> ConvertToDecimal(string v, List<string> newList)
    {
        if(v.Equals("-1")){
            newList.Add("-1");
            return newList;
        }
        List<string> temp = new List<string>();
        temp = ConvertToBinary(v, temp);
        string output = temp[0].ToString();
        int power = output.Length-1;
        int value = 0;
        for(int i = 0; i < output.Length; i++){
            string s = output[i].ToString();
            int x = Int32.Parse(s);
            value += x * (int)Math.Pow(2, power);
            power--;
        }
        newList.Add(value.ToString());
        return newList;
    }
}