/*
    Created by Timothy Garrett
    6/22/18
 */
using System;

class VigenereCipher
{   
    /*
        4 arguments will be passed in 
        args[0] = keyword
        args[1] = e/d // keyword must be the same for both encrypting and decrypting
        args[2] = input.txt
        args[3] = output.txt
     */
    static void Main(string[] args)
    {
        if(args[1].Equals("e")){
            string plaintext = FileHandler.ReadFile(args[2]);
            string ciphertext = encode(plaintext, args[0]);
            FileHandler.WriteFile(ciphertext, args[3]);
        }
        else if(args[1].Equals("d")){
            string ciphertext = FileHandler.ReadFile(args[2]);
            string plaintext = decode(ciphertext, args[0]);
            FileHandler.WriteFile(plaintext, args[3]);
        }   
        else{
            Console.WriteLine("The option given is invalid please make a correct selection next time.");
        } 
    }

    private static string decode(string ciphertext, string keyword)
    {
        string output = "";
        int [] key = new int [ciphertext.Length];
        for(int i = 0; i < ciphertext.Length; i++){
            if(Char.IsLower(keyword[i%7]))
                key[i] = (int)keyword[i%7] - 97;
            else if(Char.IsUpper(keyword[i%7]))
                key[i] = (int)keyword[i%7] - 65;
            
            char ciphertextChar = ciphertext[i];
            char plainChar;
            if(Char.IsLetter(ciphertextChar)){
                if(Char.IsLower(ciphertextChar)){
                    int keyValue = key[i];
                    int cipherValue = (int)ciphertextChar - 97;
                    int plainValue;
                    if(cipherValue - keyValue < 0)
                        plainValue = 26 + (cipherValue-keyValue);
                    else
                        plainValue = cipherValue - keyValue;

                    plainChar = (char)(plainValue + 97);
                    output = String.Concat(output, plainChar);
                }
                else if(Char.IsUpper(ciphertextChar)){
                    int keyValue = key[i];
                    int cipherValue = (int)ciphertextChar - 65;
                    int plainValue;
                    if(cipherValue - keyValue < 0)
                        plainValue = 26 + (cipherValue - keyValue);
                    else
                        plainValue = cipherValue - keyValue;

                    plainChar = (char)(plainValue + 65);
                    output = String.Concat(output, plainChar);
                }
            }
            else
                output = String.Concat(output, ciphertextChar);
        } 
        return output;
    }

    private static string encode(string plaintext, string keyword)
    {
        string output = "";
        int [] key = new int [plaintext.Length];
        for(int i = 0; i < plaintext.Length; i++){
            if(Char.IsLower(keyword[i%7]))
                key[i] = (int)keyword[i%7] - 97;
            else if(Char.IsUpper(keyword[i%7]))
                key[i] = (int)keyword[i%7] - 65;
            
            char plaintextChar = plaintext[i];
            char cipherChar;
            if(Char.IsLetter(plaintextChar)){
                if(Char.IsLower(plaintextChar)){
                    int keyValue = key[i];
                    int plainValue = (int)plaintextChar - 97;
                    int cipherValue = (keyValue + plainValue) % 26;
                    cipherChar = (char)(cipherValue + 97);
                    output = String.Concat(output, cipherChar);
                }
                else if(Char.IsUpper(plaintextChar)){
                    int keyValue = key[i];
                    int plainValue = (int)plaintextChar - 65;
                    int cipherValue = (keyValue + plainValue) % 26;
                    cipherChar = (char)(cipherValue + 65);
                    output = String.Concat(output, cipherChar);
                }
            }
            else
                output = String.Concat(output, plaintextChar);
        } 
        return output;
    }
}
