public class Sort_Array_By_Parity{
    public int[] sortArrayByParity(int[] A) {
        int[] arr = A.clone();
        int cnt = 0;
        for(int i = 0; i < A.length; i++)
            if(Math.abs(A[i]) % 2 == 0)
                arr[cnt++] = A[i];
        
        for(int i = 0; i < A.length; i++)
            if(Math.abs(A[i]) % 2 == 1)
                arr[cnt++] = A[i];
        
        return arr;
    }
}