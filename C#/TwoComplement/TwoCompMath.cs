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
    private static bool IsThereOverflow(bool num1, bool num2, bool sum)
    {
        if(num1 != num2)
            return false;
        else{
            if(num1 != sum){
                Console.Error.WriteLine("THERE WAS OVERFLOW!!!!!");
                return true;
            }
            return false;
        }
    }

    private static bool checkPositivity(char number)
    {
        if(number.Equals('0'))
            return true;
        return false;
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
        bool num1 = false;
        bool num2 = false;
        bool sum = false;
        string addition = "";
        int decOutput = 0;
        for(int i = 0; i < list.Count; i = i+2){
            var tup = new Tuple<string, string>(list[i], list[i+1]);
            num1 = checkPositivity(tup.Item1[0]);
            num2 = checkPositivity(tup.Item2[0]);
            addition = String.Concat(BinaryAddition(tup), addition);
            sum = checkPositivity(addition[0]);
            if(!IsThereOverflow(num1, num2, sum)){
                decOutput = DecimalExpansion.ExpansionMath(addition);
                output.Add(decOutput);
            }
        }
        return output;
    }
    private static List<string> AdditionOutputToBin(List<string> list)
    {
        List<string> output = new List<string>();
        bool num1 = false;
        bool num2 = false;
        bool sum = false;
        string addition = "";
        for(int i = 0; i < list.Count; i = i+2){
            var tup = new Tuple<string, string>(list[i], list[i+1]);
            num1 = checkPositivity(tup.Item1[0]);
            num2 = checkPositivity(tup.Item2[0]);
            addition = BinaryAddition(tup);
            sum = checkPositivity(addition[0]);
            if(!IsThereOverflow(num1, num2, sum))
                output.Add(addition);
        }
        return output;
    }
    private static List<int> SubtractionOutputToDec(List<string> list)
    {
        List<int> output = new List<int>();
        bool num1 = false;
        bool num2 = false;
        bool sum = false;
        string subtraction = "";
        int decOutput = 0;
        for(int i = 0; i < list.Count; i = i+2){
            var tup = new Tuple<string, string>(list[i], NegateNumber.flipBits(list[i+1]));
            num1 = checkPositivity(tup.Item1[0]);
            num2 = checkPositivity(tup.Item2[0]);
            subtraction = BinaryAddition(tup);
            sum = checkPositivity(subtraction[0]);
            if(!IsThereOverflow(num1, num2, sum)){
                decOutput = DecimalExpansion.ExpansionMath(subtraction);
                output.Add(decOutput);
            }
        }
        return output;
    }

    private static List<string> SubtractionOutputToBin(List<string> list)
    {
        List<string> output = new List<string>();
        bool num1 = false;
        bool num2 = false;
        bool sum = false;
        string subtraction = "";
        for(int i = 0; i < list.Count; i = i+2){
            var tup = new Tuple<string, string>(list[i], NegateNumber.flipBits(list[i+1]));
            num1 = checkPositivity(tup.Item1[0]);
            num2 = checkPositivity(tup.Item2[0]);
            subtraction = BinaryAddition(tup);
            sum = checkPositivity(subtraction[0]);
            if(!IsThereOverflow(num1, num2, sum))
                output.Add(subtraction);
        }
        return output;
    }
}