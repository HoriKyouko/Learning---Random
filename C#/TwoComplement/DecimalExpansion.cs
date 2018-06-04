/*
    Created by Timothy Garrett
    6/4/18
 */
using System;
using System.Collections.Generic;

class DecimalExpansion
{
    static void Main(string[] args)
    {
        List<string> list = FileHandler<string>.ReadFile(args[0]);
        List<int> outputList = new List<int>();
        
        for(int i = 0; i < list.Count; i++){
            if(list[i].Equals("-1"))
                outputList.Add(-1);
            else{
                bool MSBvalue = MSB(list[i][0]);
                string word = list[i];
                if(MSBvalue){
                    int power = word.Length-1;
                    int value = 0;
                    for(int j = 0; j < word.Length; j++){
                        string s = word[j].ToString();
                        int temp = Int32.Parse(s);
                        if(j == 0)
                            temp = -temp;
                        value += temp * (int)Math.Pow(2, power);
                        power--;
                    }
                    outputList.Add(value);
                }
                else{
                    int power = word.Length-1;
                    int value = 0;
                    for(int j = 0; j < word.Length; j++){
                        string s = word[j].ToString();
                        int temp = Int32.Parse(s);
                        value += temp * (int)Math.Pow(2, power);
                        power--;
                    }
                    outputList.Add(value);
                }
            }
        }
        FileHandler<int>.WriteFile(outputList,args[1]);
    }

    static bool MSB(char binary){
        if(binary.Equals('1'))
            return true;
        return false;
    }
}
