/*
    Created by Timothy Garrett
    6/21/18
 */
using System;
using System.Collections.Generic;
using System.Linq;

class MonoalphabeticCipher
{
    /*
        To use you have to give it a set of parameters for the
        arguements. These paramaters are as follows: a seed
        of some integer value, either the characters e or d,
        your input file location, and output file location.
        
        The Seed is to set the random for generating out random
        alphabet which is used in both encoding and decoding
        else if you decoded a different seed you'd just end up
        with garbled words still.

        The characters e or d stand for encode and decode
        respectively and any other characters or strings
        will cause an error to be thrown.

        The input and output file locations should be self
        explanatory.
     */
    static void Main(string[] args)
    {
        char[] alphabet = createAlphabet();
        char[] randAlphabet = createRandAlphabet(args[0]);
        var pairs = createPairs(alphabet, randAlphabet);

        List<string> list = new List<string>();
        if(args[1].Equals("e")){
            list = FileHandler.ReadFile(args[2]);
            list = encode(list, pairs);
            FileHandler.WriteFile(list, args[3]);
        }
        else if(args[1].Equals("d")){
            list = FileHandler.ReadFile(args[2]);
            list = decode(list, pairs);
            FileHandler.WriteFile(list, args[3]);
        }
        else{
            Console.WriteLine("The option given is invalid please make a correct selection next time.");
        }
        
    }

    private static List<string> encode(List<string> list, List<(List<(char, char)>, List<(char, char)>)> pairs)
    {
        List<string> outputList = new List<string>();
        for(int i = 0; i < list.Count; i++){
            char[] temp = list[i].ToCharArray();
            int count = 0;
            int j = 0;
            string comStr = "";
            while(count != temp.Length){
                if(Char.IsLetter(temp[count]) && pairs[j].Item1[0].Item1.Equals(temp[count])){
                    comStr = String.Concat(comStr, pairs[j].Item1[0].Item2);
                    count++;
                    j = 0;
                }
                else if(Char.IsLetter(temp[count]) && pairs[j].Item2[0].Item1.Equals(temp[count])){
                    comStr = String.Concat(comStr, pairs[j].Item2[0].Item2);
                    count++;
                    j = 0;
                }
                else if(!Char.IsLetter(temp[count])){
                    comStr = String.Concat(comStr, temp[count]);
                    count++;
                    j = 0; 
                }
                else{
                    j++;
                }
            }
            outputList.Add(comStr);
        }
        return outputList;
    }
    
    private static List<string> decode(List<string> list, List<(List<(char, char)>, List<(char, char)>)> pairs)
    {
        List<string> outputList = new List<string>();
        for(int i = 0; i < list.Count; i++){
            char[] temp = list[i].ToCharArray();
            int count = 0;
            int j = 0;
            string comStr = "";
            while(count != temp.Length){
                if(Char.IsLetter(temp[count]) && pairs[j].Item1[0].Item2.Equals(temp[count])){
                    comStr = String.Concat(comStr, pairs[j].Item1[0].Item1);
                    count++;
                    j = 0;
                }
                else if(Char.IsLetter(temp[count]) && pairs[j].Item2[0].Item2.Equals(temp[count])){
                    comStr = String.Concat(comStr, pairs[j].Item2[0].Item1);
                    count++;
                    j = 0;
                }
                else if(!Char.IsLetter(temp[count])){
                    comStr = String.Concat(comStr, temp[count]);
                    count++;
                    j = 0; 
                }
                else{
                    j++;
                }
            }
            outputList.Add(comStr);
        }
        return outputList;
    }

    private static char[] createRandAlphabet(string s)
    {
        int seed = Int32.Parse(s);
        char[] temp = new char[26];
        var value = Enumerable.Range(0,26).Select(x =>(int)x).ToArray();
        for (int i = 25; i >= 0; i--){
            int j = new Random(seed).Next(0, i);
            int temp2 = value[i];
            value[i] = value[j];
            value[j] = temp2;
            temp[i] = (char)(value[i] + 97);
        }
        return temp;
    }

    private static char[] createAlphabet()
    {
        char[] temp = new char[26];
        for (int i = 0; i < 26; i++)
            temp[i] = (char)(i + 97);
        return temp;
    }
    /*Probably could have just said a list of touples... */
    private static List<(List<(char, char)>, List<(char, char)>)> createPairs(char[] alphabet, char[] randAlphabet)
    {
        var pairs = new List<(List<(char, char)>, List<(char, char)>)>();
        for(int i = 0; i < alphabet.Length; i+=2){
            var first = new List<(char, char)>();
            var second = new List<(char, char)>();
            first.Add((alphabet[i], randAlphabet[i]));
            second.Add((alphabet[i+1], randAlphabet[i+1]));
            pairs.Add((first, second));
        }
        return pairs;
    }
}
