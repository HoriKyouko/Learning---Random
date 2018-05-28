using System;
using System.Collections.Generic;

class BinarySystem{
    public static List<string> addToList(string number, int size, List<string> outputList){
        // We simply add a -1 to denote there was an exception.
        if(number.Equals("-1")){
            outputList.Add("-1");
            return outputList;
        }
        string output = "";
        int temp = size-1;
        for(int i = 0; i < size; i++){
                if(i == (size-1))
                    output = String.Concat(output, number[i] + "*2^" + temp);
                else
                    output = String.Concat(output, number[i] + "*2^" + temp + " + ");
                temp--;
            }
        outputList.Add(output);
        return outputList;
    }

    public static List<string> compute(List<string> list){
        List<string> newList = new List<string>();
        for(int i = 0; i < list.Count; i++)
            newList = DecimalSystem.addToList(list[i], list[i].Length, newList);
        return newList;
    }
}