/*
    Created by Timothy Garrett
    6/6/18
 */
using System;
using System.Collections.Generic;
using System.Linq;

class KeywordCipher
{
    static void Main(string[] args)
    {
        Randomizer rand = new Randomizer(1, 10, checkedSeed(args[0]));
        HashSet<char> uniqueSetOfChars = new HashSet<char>();
        Tuple<char, char> tuple;
        int length = rand.RandomValue();
        var list = new List<Tuple<char, char>>();
        char[] keyword = rand.RandomChar(length);

        for(int i = 0; i < length; i++)
            uniqueSetOfChars.Add(keyword[i]);
        
        for(int i = 97; i < 123; i++){
            uniqueSetOfChars.Add((char)i);
            tuple = new Tuple<char, char>((char)i, uniqueSetOfChars.ElementAt(i-97));
            list.Add(tuple);
        }
        // From here we would then check if the value from the text is the same one of our values in our hashset and
        // give the corresponding pair to output in our string.
        for(int i = 0; i < length; i++){
            Console.WriteLine(list[i].Item2);
        }
    }

    private static int checkedSeed(string v)
    {
        int value = 0;
        bool result = Int32.TryParse(v, out value);
        if(result){
            return value;
        }
        else{
            return 0;
        }
    }
}
