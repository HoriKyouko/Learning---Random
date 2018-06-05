/*
    Created by Timothy Garrett
    6/5/18
 */
using System;
using System.Collections.Generic;

class NegateNumber{
    public static List<int> compute(List<string> list){
        List<int> output = new List<int>();
        for(int i = 0; i < list.Count; i++){
            string temp = flipBits(list[i]);
            output.Add(DecimalExpansion.ExpansionMath(temp));
        }
        return output;
    }
    private static string flipBits(string v)
    {
        if(v.Equals("-1"))
            return("-1");
        string output = "";
        for(int i = 0; i < v.Length; i++){
            if(v[i] == '0')
                output = String.Concat(output, "1");
            else if(v[i] == '1')
                output = String.Concat(output, "0");
        }
        output = addOneBit(output);
        return output;
    }
    private static string addOneBit(string output)
    {
        bool metZero = false;
        string newOutput = "";
        for(int i = output.Length - 1; i >= 0; i--){
            if(output[i] == '1' && !metZero)
                newOutput = String.Concat("0", newOutput);
            else if(output[i] == '0' && !metZero){
                newOutput = String.Concat("1", newOutput);
                metZero = true;
            }
            else
                newOutput = String.Concat(output[i], newOutput);
        }
        return newOutput;
    }
}