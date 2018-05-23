using System;
using System.IO;
/*

    ASCII has A..Z as 65->90 and a..z as 97->122.

 */
class CaesarCipher
{
    static void Main(string[] args)
    {
        Random rand = new Random();
        int num = rand.Next() % 26;
        Console.WriteLine(num);
        encode(num);
        decode(num);
    }

    // Function encode
    static void encode(int num){
        StreamReader sr = new StreamReader("input.txt");
        StreamWriter sw = new StreamWriter("output.txt");
        while(!sr.EndOfStream){
            char temp = (char)sr.Read();
            int mod = temp;
            if(Char.IsLetter(temp)){
                if(mod + num > 90 && Char.IsUpper(temp)){
                    mod = (mod + num) - 26;
                }
                else if(mod + num > 122){
                    mod = (mod + num) - 26;
                }
                else{
                    mod = mod + num;
                }
                temp = (char) mod;
            }
            sw.Write(temp);
        }
        sr.Dispose();
        sw.Dispose();
    }
    // Function decode
    static void decode(int num){
        StreamReader sr = new StreamReader("output.txt");
        StreamWriter sw = new StreamWriter("input2.txt");
        while(!sr.EndOfStream){
            char temp = (char)sr.Read();
            int mod = temp;
            if(Char.IsLetter(temp)){
                if(mod - num < 65){
                    mod = (mod - num) + 26;
                }
                else if(mod - num < 97 && Char.IsLower(temp)){
                    mod = (mod - num) + 26;
                }
                else{
                    mod = mod - num;
                }
                temp = (char) mod;
            }
            sw.Write(temp);
        }
        sr.Dispose();
        sw.Dispose();
    }
}
