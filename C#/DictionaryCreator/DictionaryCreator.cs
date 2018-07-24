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
            char[] alphabet = new char[args.Length-1];
            for(int i = 1; i < args.Length; i++)
                alphabet[i-1] = Char.Parse(args[i]);
            List<string> dictionary = buildDictionary(alphabet);
            for(int i = 0; i < alphabet.Length; i++)
                Console.WriteLine(alphabet[i]);
        }

        private static List<string> buildDictionary(char[] alphabet)
        {
            List<string> dictionary = new List<string>();
            StreamWriter sw = new StreamWriter("output.txt");
            dictionary = buildDictionary(alphabet, 0, alphabet.Length-1, sw);
            sw.Close();
            return dictionary;
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