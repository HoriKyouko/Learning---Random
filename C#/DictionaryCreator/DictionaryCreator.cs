using System;
using System.Collections.Generic;
using System.IO;
/*
    Was meant to help me figure out words for WordScape, but failed because
    there are so many permutations possible if you don't reduce obvious failures
    in the english alphabet i.e a word starting with rv that isn't rv or rvs in
    this case.
 */
namespace DictionaryCreater
{
    class DictionaryCreater
    {
        static void Main(string[] args)
        {
            int length = Int32.Parse(args[0]);
            /*char[] alphabet = new char[length];
            for(int i = 0; i < length; i++)
                alphabet[i] = Char.Parse(args[i+1]);*/
            // Why am I storing this here I never use dictionary???
            buildDictionary(length);
            /*for(int i = 0; i < alphabet.Length; i++)
                Console.WriteLine(alphabet[i]);*/
        }

        private static void buildDictionary(int length)
        {
            int [] convertedAlphabet = new int [length];
            /*for(int i = 0; i < convertedAlphabet.Length; i++)
                convertedAlphabet[i] = alphabet[i] - 97;
            */
            //List<string> dictionary = new List<string>();
            using(StreamWriter sw = new StreamWriter("output.txt")){
                for(int i = 0; i < 26; i++){
                    for(int j = 0; j < 26; j++){
                        for(int k = 0; k < 26; k++){
                            for(int l = 0; l < 26; l++){
                                for(int m = 0; m < 26; m++){
                                    for(int n = 0; n < 26; n++){
                                        for(int o = 0; o < 26; o++){
                                            for(int p = 0; p < 26; p++){
                                                createElement(i, j, k, l, m, n, o, p, sw);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            //dictionary = buildDictionary(alphabet, 0, alphabet.Length-1, sw);
        }

        private static void createElement(int i, int j, int k, int l, int m, int n, int o, int p, StreamWriter sw)
        {
            int [] temp = new int[]{i,j,k,l,m,n,o,p};
            string value = "";
            for(int x = 0; x < temp.Length; x++){
                char character = Convert.ToChar(temp[x] + 97);
                value = value + character;
            }
            sw.WriteLine(value);
        }

        private static List<string> buildDictionary(char[] alphabet, int v1, int v2, StreamWriter sw)
        {
            List<string> dictionary = new List<string>();
            if(v1 == v2){
                dictionary.Add(new string(alphabet));
                sw.WriteLine(alphabet);
                Console.WriteLine(alphabet);
                return dictionary;
            }
            else{
                for(int i = v1; i <= v2; i++){
                    swap(ref alphabet[v1], ref alphabet[i]);
                    buildDictionary(alphabet, v1 + 1, v2, sw);
                    swap(ref alphabet[v1], ref alphabet[i]);
                }
            }
            return dictionary;
        }

        private static void swap(ref char v1, ref char v2)
        {
            if(v1 == v2){
                return;
            }
            v1 ^= v2;
            v2 ^= v1;
            v1 ^= v2;
        }
    }
}