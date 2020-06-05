public class Valid_Mountain_Array {
    public boolean validMountainArray(int[] A) {
        int i = 0;
        int len = A.length;
        
        while(i+1 < len && A[i] < A[i+1])
            i++;
        
        if(i == 0 || i == len-1)
            return false;
        
        while(i+1 < len && A[i] > A[i+1])
            i++;
        
        return i == len-1;
    }
}