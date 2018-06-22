/*
    Created by Timothy Garrett
    6/22/18
 */
using System;
using System.IO;

internal class FileHandler
{
    internal static string ReadFile(string filename)
    {
        string output = "";
        using(StreamReader sr = new StreamReader(filename))
            output = sr.ReadToEnd();
        return output;
    }

    internal static void WriteFile(string text, string filename){
        using(StreamWriter sw = new StreamWriter(filename))
            for(int i = 0; i < text.Length; i++)
                sw.Write(text[i]);
    }
}