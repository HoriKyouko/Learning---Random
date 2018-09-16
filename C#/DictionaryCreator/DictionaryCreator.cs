using System;
using System.Diagnostics;
using System.IO;
/*
    An idea to repurpose this into a password generator...
    Lets just say it didn't finish without running out of space
    and that was only for 26^8...
 */
namespace DictionaryCreater
{
    class DictionaryCreater
    {
        static void Main(string[] args)
        {
            buildDictionary(Int32.Parse(args[0]));
        }

        private static void buildDictionary(int length)
        {
            Stopwatch stopwatch = new Stopwatch();
            using(StreamWriter sw = new StreamWriter("E:\\output.txt")){
                stopwatch.Start();
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
                stopwatch.Stop();
                TimeSpan ts = stopwatch.Elapsed;
                string elapsedTime = String.Format("{0:00}:{1:00}:{2:00}:{3:00}", ts.Hours, ts.Minutes, ts.Seconds, ts.Milliseconds/10);
                Console.WriteLine("Elapsed Time: " + elapsedTime);
            }
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
    }
}