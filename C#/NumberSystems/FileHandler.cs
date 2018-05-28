using System;
using System.IO;
using System.Collections.Generic;
class FileHandler{
    /*
        Simple function for reading from a file and adding it to a list and then returning
        that list. Only works if each entry is a single line.
     */
    public static List<string> ReadFile(string fileName){
        List<string> fileList = new List<string>();
        using(StreamReader sr = new StreamReader(fileName))
            while(!sr.EndOfStream)
                fileList.Add(sr.ReadLine());
        return fileList;
    }
    /*
        Simple function for writing a given list to an output file. Doesn't return anything
        but each entry is on it's own line.
     */
    public static void WriteFile(List<string> list, string fileName){
        using(StreamWriter sw = new StreamWriter(fileName))
            for(int i = 0; i < list.Count; i++)
                sw.WriteLine(list[i]);
    }
}