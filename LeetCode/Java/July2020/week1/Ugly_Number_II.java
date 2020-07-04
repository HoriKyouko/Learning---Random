public class Ugly_Number_II {
    public int nthUglyNumber(int n) {
        int [] output = new int[n];
        output[0] = 1;
        int L1 = 2;
        int L2 = 3;
        int L3 = 5;
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        for(int i = 1; i < n; i++){
            int min = Math.min(L1, Math.min(L2, L3));
            output[i] = min;
            if(L1 == min)
                L1 = 2 * output[++index2];
            if(L2 == min)
                L2 = 3 * output[++index3];
            if(L3 == min)
                L3 = 5 * output[++index5];
        }
        
        return output[n-1];
    }
}