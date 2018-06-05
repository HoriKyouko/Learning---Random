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
            output.Add(Expansion(temp));
        }
        return output;
    }
    /*
        Should probably go back and make Expansion its own class since I use this exact same formula
        in DecimalExpansion. The reason I built it the way I did in DecimalExpansion on the first
        iteration is because I was being lazy and didn't want to create another .cs file and I didn't
        have the foresight to see that it would be necessary and useful in the rest of my two's
        complement number programs that I want to mess with.
     */
    private static int Expansion(string word)
    {
        bool MSBvalue = MSB(word[0]);
        int power = word.Length - 1;
        int value = 0;
        if (MSBvalue)
        {
            for (int j = 0; j < word.Length; j++)
            {
                string s = word[j].ToString();
                int temp = Int32.Parse(s);
                if (j == 0)
                    temp = -temp;
                value += temp * (int)Math.Pow(2, power);
                power--;
            }
        }
        else
        {
            for (int j = 0; j < word.Length; j++)
            {
                string s = word[j].ToString();
                int temp = Int32.Parse(s);
                value += temp * (int)Math.Pow(2, power);
                power--;
            }
        }
        return value;
    }

    private static bool MSB(char v)
    {
        if(v.Equals('1'))
            return true;
        return false;
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