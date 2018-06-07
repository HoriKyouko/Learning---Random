/*
    Created by Timothy Garrett
    6/6/18
 */
using System;

class KeywordCipher
{
    static void Main(string[] args)
    {
        Randomizer rand = new Randomizer(1, 4);
        char temp = (char)(rand.RandomValue()+96);
        Console.WriteLine(temp);
    }
}
