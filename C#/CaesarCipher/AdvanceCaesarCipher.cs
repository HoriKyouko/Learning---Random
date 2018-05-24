/*  
    Created by Timothy Garrett
    5/24/2018
 */
using System;
using System.IO;
/*
    Takes the input of whatever text document you want and the output document
    along with a integer and creates a bigger variety of changes in encoding
    and decoding the documents. All based on ASCII would need to change
    certain values for it to be useable in Unicode.
 */
class AdvanceCaesarCipher{
    public void encode(string input, string output, int numbers){
        numbers = numbers % 95;
        StreamReader sr = new StreamReader(input);
        StreamWriter sw = new StreamWriter(output);
        while(!sr.EndOfStream){
            char temp = (char)sr.Read();
            int mod = temp;
            if(!Char.IsControl(temp)){
                if(mod + numbers > 126)
                    mod = (mod + numbers) - 95;
                else
                    mod = mod + numbers;
                temp = (char) mod;
            }
            sw.Write(temp);
        }
        sr.Dispose();
        sw.Dispose();
    }

    public void decode(string input, string output, int numbers){
        numbers = numbers % 95;
        StreamReader sr = new StreamReader(input);
        StreamWriter sw = new StreamWriter(output);
        while(!sr.EndOfStream){
            char temp = (char)sr.Read();
            int mod = temp;
            if(!Char.IsControl(temp)){
                if(mod - numbers < 32)
                    mod = (mod - numbers) + 95;
                else
                    mod = mod - numbers;
                temp = (char) mod;
            }
            sw.Write(temp);
        }
        sr.Dispose();
        sw.Dispose();
    }
}