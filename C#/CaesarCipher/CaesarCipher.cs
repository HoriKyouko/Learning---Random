/*  
    Created by Timothy Garrett
    5/23/2018
 */
using System;
using System.IO;
/*
    Implementation of a simple Caesar Cipher and a more advance Caesar Cipher,
    if you can call just allowing punctuation and numbers to be in it advance,
    for encoding text documents and decoding them.All based on ASCII would need 
    to change certain values for it to be useable in Unicode.
 */
class CaesarCipher
{
    static void Main(string[] args)
    {
        int rand = new Random().Next();
        encode(rand);
        decode(rand);
        AdvanceCaesarCipher acc = new AdvanceCaesarCipher();
        acc.encode("input.txt", "output2.txt", rand);
        acc.decode("output2.txt", "input3.txt", rand);
    }

    // Function encode
    static void encode(int num){
        num = num % 26;
        StreamReader sr = new StreamReader("input.txt");
        StreamWriter sw = new StreamWriter("output.txt");
        while(!sr.EndOfStream){
            char temp = (char)sr.Read();
            int mod = temp;
            if(Char.IsLetter(temp)){
                if(mod + num > 90 && Char.IsUpper(temp))
                    mod = (mod + num) - 26;
                else if(mod + num > 122)
                    mod = (mod + num) - 26;
                else
                    mod = mod + num;
                temp = (char) mod;
            }
            sw.Write(temp);
        }
        sr.Dispose();
        sw.Dispose();
    }
    // Function decode
    static void decode(int num){
        num = num % 26;
        StreamReader sr = new StreamReader("output.txt");
        StreamWriter sw = new StreamWriter("input2.txt");
        while(!sr.EndOfStream){
            char temp = (char)sr.Read();
            int mod = temp;
            if(Char.IsLetter(temp)){
                if(mod - num < 65)
                    mod = (mod - num) + 26;
                else if(mod - num < 97 && Char.IsLower(temp))
                    mod = (mod - num) + 26;
                else
                    mod = mod - num;
                temp = (char) mod;
            }
            sw.Write(temp);
        }
        sr.Dispose();
        sw.Dispose();
    }
}
