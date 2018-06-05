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
        /*
            This was a bit tricky to get to work and shows that I need to work on my Generics more,
            but I was able to get the program to properly work by doing the following below. 
            If you want to have an output of a string of binary you have to change the code to be:
            
            FileHandler<string>.WriteFile(TwoCompMath<string>.compute(list, true, typeof(string)), args[1]);
            
            else if you want it have an output of a integer value you have to change the code to be:

            FileHandler<int>.WriteFile(TwoCompMath<int>.compute(list, true, typeof(int)), args[1]);

            This all of course is dealing with Addition if you wanted to do subtraction you would change
            the bool value in the compute method from true to false.
         */
        FileHandler<string>.WriteFile(TwoCompMath<string>.compute(list, false, typeof(string)), args[1]);

        // For ease of switching between binary and int.
        
        //FileHandler<int>.WriteFile(TwoCompMath<int>.compute(list, false, typeof(int)), args[1]);   
    }
}
