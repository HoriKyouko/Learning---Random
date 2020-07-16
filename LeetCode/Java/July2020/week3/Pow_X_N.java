public class Pow_X_N {
    public double myPow(double x, int n) {
        if(n == 0 || x == 1.0) return 1;
        if(n == 1) return x;
        
        if(x > 0 && n == Integer.MIN_VALUE) return 0;
        if(x < 0 && n == Integer.MIN_VALUE) return 1;
        if(x > 0 && n == Integer.MAX_VALUE) return 0;
        if(x < 0 && n == Integer.MAX_VALUE) return -1;
        
        double val = 1;
        boolean isNegative = (n < 0);
        
        val = fastPow(x, n);
        
        return isNegative ? 1/val : val;
    }
    
    double fastPow(double x, int n){
        if(n == 0)
            return 1;
        double half = fastPow(x, n/2);
        if(n % 2 == 0)
            return half*half;
        else
            return half * half * x;
    }
}