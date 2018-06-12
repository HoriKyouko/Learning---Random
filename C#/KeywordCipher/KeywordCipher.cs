/*
    Created by Timothy Garrett
    6/6/18
 */
using System;
using System.Collections.Generic;
using System.Linq;

class KeywordCipher
{
    /*
        To use you have to give it a set of parameters for the
        arguements. These paramaters are as follows: a seed
        of some integer value, either the characters e or d,
        your input file location, and output file location.
        
        The Seed is to set the randomizer for both encoding
        and decoding else if you decoded a different seed
        you'd just end up with garbled words still.

        The characters e or d stand for encode and decode
        respectively and any other characters or strings
        will cause an error to be thrown.

        The input and output file locations should be self
        explanatory.
     */
    static void Main(string[] args)
    {
        List<(char, char)> keyword = CreateKeyword(args);
        List<string> list = new List<string>();
        if(args[1].Equals("e")){
            list = FileHandler.ReadFile(args[2]);
            list = encode(list, keyword);
            FileHandler.WriteFile(list, args[3]);
        }
        else if(args[1].Equals("d")){
            list = FileHandler.ReadFile(args[2]);
            list = decode(list, keyword);
            FileHandler.WriteFile(list, args[3]);
        }
        else{
            Console.WriteLine("The option given is invalid please make a correct selection next time.");
        }
    }

    private static List<string> encode(List<string> list, List<(char alphabet, char keychar)> keyword)
    {
        List<string> outputList = new List<string>();
        for(int i = 0; i < list.Count; i++){
            char[] temp = list[i].ToCharArray();
            int count = 0;
            int j = 0;
            string comStr = "";
            bool zero = false;
            while(count != temp.Length){
                zero = false;
                if(Char.IsLetter(temp[count]) && keyword[j].alphabet.Equals(temp[count])){
                    comStr = String.Concat(comStr, keyword[j].keychar);
                    count++;
                    j = 0;
                    zero = true;
                }
                else if(!Char.IsLetter(temp[count])){
                    comStr = String.Concat(comStr, temp[count]);
                    count++;
                    j = 0;
                    zero = true;
                }
                if(!zero)
                    j++;
            }
            outputList.Add(comStr);
        }
        return outputList;
    }
    private static List<string> decode(List<string> list, List<(char alphabet, char keychar)> keyword)
    {
        List<string> outputList = new List<string>();
        for(int i = 0; i < list.Count; i++){
            char[] temp = list[i].ToCharArray();
            int count = 0;
            int j = 0;
            string comStr = "";
            bool zero = false;
            while(count != temp.Length){
                zero = false;
                if(Char.IsLetter(temp[count]) && keyword[j].keychar.Equals(temp[count])){
                    comStr = String.Concat(comStr, keyword[j].alphabet);
                    count++;
                    j = 0;
                    zero = true;
                }
                else if(!Char.IsLetter(temp[count])){
                    comStr = String.Concat(comStr, temp[count]);
                    count++;
                    j = 0;
                    zero = true;
                }
                if(!zero)
                    j++;
            }
            outputList.Add(comStr);
        }
        return outputList;
    }

    private static List<(char, char)> CreateKeyword(string[] args)
    {
        Randomizer rand = new Randomizer(from: 1, seed: checkedSeed(args[0]), to: 43);
        HashSet<char> uniqueSetOfChars = new HashSet<char>();
        char[] keyword = rand.RandomChar(rand.RandomValue());

        for (int i = 0; i < keyword.Length; i++)
            uniqueSetOfChars.Add(keyword[i]);

        var list = new List<(char, char)>();
        for (int i = 97; i < 123; i++)
        {
            uniqueSetOfChars.Add((char)i);
            list.Add(((char)i, uniqueSetOfChars.ElementAt(i-97)));
        }
        return list;
    }

    private static int checkedSeed(string v)
    {
        int value = 0;
        bool result = Int32.TryParse(v, out value);
        if(result)
            return value;
        else
            return 0;
    }
}
