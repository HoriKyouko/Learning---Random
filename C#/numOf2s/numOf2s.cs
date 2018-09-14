/*
    Created by Timothy Garrett
    9/14/18
 */
 
 /*
    This program is meant to count the number of 2s from 0 to n.
    Mind you 22 counts as 2 and any form that considers 22 will have
    at least 2 counts added.

    Current runtime: O(N*M) (I think it's M since the for loop length does increase by 1 after every power of ten).
    Space Complexity: O(n)
  */
using System;
class numOf2s
{
    static void Main(string[] args)
    {
        //Takes a number n and creates an array from 0 to n inclusive
        int x = Convert.ToInt32(args[0]);
        int [] numbers = new int[x+1];
        // Since were using an array have to populate the array after we create it.
        for(int i = 0; i <= x; i++)
            numbers[i] = i;
        // Control variables for determining if it is a 2 and to count them and printing them.
        bool flag = false;
        int counter = 0;
        int count = 0;
        // Loops through all our elements could have used a foreach loop but thought
        // of it after I already made the while loop and most of the code inside.
        while(count <= x){
            string temp = numbers[count].ToString();
            // Checks if our number contains a 2
            for(int i = 0; i < temp.Length; i++){
                char elem = temp[i];
                if(elem.Equals('2')){
                    flag = true;
                    counter++;
                }
            }
            // If it does we print the number out and then increment our count value and return our flag to false.
            if(flag)
                Console.Write(temp + " ");
            count++;
            flag = false;
        }
        Console.WriteLine("\n" + counter);
    }
}
