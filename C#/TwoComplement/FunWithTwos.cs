/*
    Created by Timothy Garrett
    6/5/18
 */
using System;
using System.Collections.Generic;

class FunWithTwos{
    static void Main(string[] args)
    {
        List<string> list = FileHandler<string>.ReadFile(args[0]);
        FileHandler<int>.WriteFile(DecimalExpansion.compute(list), args[1]);   
    }
}
