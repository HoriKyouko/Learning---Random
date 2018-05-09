using System;

namespace myapp
{
    class Program
    {
        static void Main(string[] args)
        {
            Test tester = new Test();
            tester.testing();
            Console.WriteLine("Hello World!");
            Console.WriteLine("TEST TO SEE IF ITS A ONE TIME THING");
            Console.WriteLine("Second TEST TO SEE IF ITS A ONE TIME THING");
        }
    }
    
    class Test{
        public void testing(){
            bool var1 = Int64.Equals(3,3);
            int var2 = 0;

            for(int j = 0; j < 10; j++){
                for(int i = 0; i < 10; i++){
                    var2 = var2 + 2;
                }
            }
            Console.WriteLine("Value of Var2: " + var2);
        }
    }
}
