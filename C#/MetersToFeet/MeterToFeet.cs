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
            
            while(true){
                if(temp >= 1)
                    inches += 39.3701;
                else
                    break;
                temp = temp - 1;
            }
            
            inches += 39.3701 * temp;
            return convertInchesToFeet(Math.Round(inches,2));
        }

        private static string convertInchesToFeet(double inches)
        {
            int foot = 0;
            double inch = inches;
            string feet;
            int numOfZeros = 1;
            int x;
            int length;
            double temp;
            
            while(true){
                if(inch / 12 >= 1)
                    foot++;
                else
                    break;
                inch = inch - 12.0;
            }

            temp = Math.Round(inch, 2);
            inch = Math.Floor(inch);
           
            while(true){
                if(temp > 1)
                    temp--;
                else
                    break;
            }
            
            length = temp.ToString().Length-2;
            
            for(int i = 0; i < length; i++)
                numOfZeros *= 10;
            
            x = (int)(Math.Round(temp, length) * numOfZeros);
            feet = foot.ToString() + "\'" + inch.ToString() + " & " + x.ToString() + "/" + numOfZeros.ToString() + "\"";
            return feet;
        }

        private static string feetToMeters(double value)
        {
            double meter = 0;
            double temp = value;
            
            while(true){
                if(temp / 39.3701 >= 1)
                    meter++;
                else   
                    break;
                temp = temp % 39.3701;
            }
            
            meter += (double)temp / 39.3701;
            return Math.Round(meter, 2).ToString() + " m";
        }
    }
}
