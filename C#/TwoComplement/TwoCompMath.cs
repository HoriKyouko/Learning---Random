using System;
using System.Collections.Generic;
using System.Linq;

class TwoCompMath<T> where T : IConvertible{
    public static List<T> compute(List<string> list, bool additionOrSubtraction, Type stringOrInt){
        if(additionOrSubtraction){
            if(stringOrInt == typeof(string))
                return (List<T>) Convert.ChangeType(AdditionOutputToBin(list), typeof(List<T>));
            else
                return (List<T>) Convert.ChangeType(AdditionOutputToDec(list), typeof(List<T>));
        }
        else{
            if(stringOrInt == typeof(string))
                return (List<T>) Convert.ChangeType(SubtractionOutputToBin(list), typeof(List<T>));
            else
                return (List<T>) Convert.ChangeType(SubtractionOutputToDec(list), typeof(List<T>));
        }
    }
    /*
        Assumes both strings will be of the same length.
     */
    private static string BinaryAddition(Tuple<string, string> tup)
    {
        string top = tup.Item1;
        string bottom = tup.Item2;
        char carry = '0';
        string output = "";
        for(int i = tup.Item1.Length - 1; i >= 0 ; i--){            
            if(top[i].Equals('0') && bottom[i].Equals('0') && carry.Equals('0')){
                output = String.Concat("0", output);
            }
            else if(top[i].Equals('0') && bottom[i].Equals('0') && carry.Equals('1')){
                output = String.Concat("1", output);
                carry = '0';
            }
            else if(((top[i].Equals('0') && bottom[i].Equals('1')) || (top[i].Equals('1') && bottom[i].Equals('0'))) && carry.Equals('0')){
                output = String.Concat("1", output);
            }
            else if(((top[i].Equals('0') && bottom[i].Equals('1')) || (top[i].Equals('1') && bottom[i].Equals('0'))) && carry.Equals('1')){
                output = String.Concat("0", output);
            }                
            else if(top[i].Equals('1') && bottom[i].Equals('1') && carry.Equals('0')){
                output = String.Concat("0", output);
                carry = '1';
            }
            else if(top[i].Equals('1') && bottom[i].Equals('1') && carry.Equals('1')){
                output = String.Concat("1", output);
            }
        }
        return output;
    }
    private static List<int> AdditionOutputToDec(List<string> list)
    {
        List<int> output = new List<int>();
        string addition = "";
        int decOutput = 0;
        for(int i = 0; i < list.Count; i = i+2){
            var tup = new Tuple<string, string>(list[i], list[i+1]);
            addition = String.Concat(BinaryAddition(tup), addition);
            decOutput = DecimalExpansion.ExpansionMath(addition);
            output.Add(decOutput);
        }
        return output;
    }
    private static List<string> AdditionOutputToBin(List<string> list)
    {
        List<string> output = new List<string>();
        string addition = "";
        for(int i = 0; i < list.Count; i = i+2){
            var tup = new Tuple<string, string>(list[i], list[i+1]);
            addition = BinaryAddition(tup);
            output.Add(addition);
        }
        return output;
    }
    private static List<int> SubtractionOutputToDec(List<string> list)
    {
        List<int> output = new List<int>();
        string subtraction = "";
        int decOutput = 0;
        for(int i = 0; i < list.Count; i = i+2){
            var tup = new Tuple<string, string>(list[i], NegateNumber.flipBits(list[i+1]));
            subtraction = BinaryAddition(tup);
            decOutput = DecimalExpansion.ExpansionMath(subtraction);
            output.Add(decOutput);
        }
        return output;
    }

    private static List<string> SubtractionOutputToBin(List<string> list)
    {
        List<string> output = new List<string>();
        string subtraction = "";
        for(int i = 0; i < list.Count; i = i+2){
            var tup = new Tuple<string, string>(list[i], NegateNumber.flipBits(list[i+1]));
            subtraction = BinaryAddition(tup);
            output.Add(subtraction);
        }
        return output;
    }
}