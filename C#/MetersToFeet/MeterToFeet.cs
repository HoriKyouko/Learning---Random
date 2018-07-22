using System;

namespace MetersToFeet
{
    class MeterToFeet
    {
        static void Main(string[] args)
        {            
            if(1 == Int32.Parse(args[0]))
                Console.WriteLine(feetToMeters(Double.Parse(args[1])));
            
            else if(2 == Int32.Parse(args[0]))
                Console.WriteLine(metersToFeet(Double.Parse(args[1])));
        }

        private static string metersToFeet(double value)
        {
            double inches = 0;
            double temp = value;
            inches = value * 39.3701;
            return convertInchesToFeet(Math.Round(inches,2));
        }

        private static string convertInchesToFeet(double inches)
        {
            int foot = 0;
            double inch = inches;
            double fractional;
            string feet;
            int numOfZeros = 1;
            int x;
            
            
            foot = (int)inches / 12;
            inch = inches - (foot * 12);

            fractional = Math.Round(inch, 2);
            inch = Math.Floor(inch);
           
            fractional = Math.Round(fractional - inch, 2);
            
            int length = fractional.ToString().Length-2;
            
            for(int i = 0; i < length; i++)
                numOfZeros *= 10;
            
            x = (int)(fractional * numOfZeros);
            feet = foot.ToString() + "\'" + inch.ToString() + " & " + x.ToString() + "/" + numOfZeros.ToString() + "\"";
            return feet;
        }

        private static string feetToMeters(double value)
        {
            double meter = 0;
            double temp = value;
            meter = value / 39.3701;
            return Math.Round(meter, 2).ToString() + " m";
        }
    }
}
