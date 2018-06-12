/*
    Created by Timothy Garrett
    6/11/18
 */
using System;
using System.Collections.Generic;
using System.IO;

class FileHandler{
    public static List<string> ReadFile(string filename){
        List<string> fileList = new List<string>();
        string str = "";
        using(StreamReader sr = new StreamReader(filename)){
            while(!sr.EndOfStream){
                char temp = (char)sr.Read();
                temp = Char.ToLower(temp);
                if(fileList.Count == 103){
                    Console.Write("");
                }
                if(temp.Equals(' ')){
                    str = String.Concat(str, temp);
                    fileList.Add(str);
                    str = "";
                } 
                else{
                    str = String.Concat(str, temp);
                }     
            }
            fileList.Add(str);
        }
        return fileList;
    }

    public static void WriteFile(List<string> list, string filename){
        using (StreamWriter sw = new StreamWriter(filename))
            for(int i = 0; i < list.Count; i++)
                sw.Write(list[i]);
    }
}