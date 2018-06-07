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
        for(int i = 0; i < 256; i++){
            char temp2 = (char)i;
            Console.WriteLine(temp2);
        }
    }
}
