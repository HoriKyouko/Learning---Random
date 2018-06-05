/*
    Created by Timothy Garrett
    6/4/18
 */
using System;
using System.Collections.Generic;

class DecimalExpansion
{

    public static List<int> compute(List<string> list){
        List<int> output = new List<int>();
        for(int i = 0; i < list.Count; i++){
            output.Add(ExpansionMath(list[i]));
        }
        return output;
    }
    /*
        This Function is public because sometimes we just need to use the ExpansionMath method
        without having to call compute first. Since we will only be passing in a string this
        works well rather than having to needlessly create the string into a List<string>. The
        other solution would be to call DecimalExpansion.compute on the list were given, once we
        change our flipBits and addOneBit method to work on all the items in the list, but this would
        cause us to enter an O(m^2 * n^2) then since we call our ExpansionMath and it has a O(m) runtime
        we would end up with an O(m^3 * n^2) runtime. Currently it is a O(n * m^3). Either way this
        is a pretty terrible runtime in my opinion even if you are dealing with just small strings (m).
     */
    public static int ExpansionMath(string word)
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

    private static bool MSB(char binary){
        if(binary.Equals('1'))
            return true;
        return false;
    }
}
