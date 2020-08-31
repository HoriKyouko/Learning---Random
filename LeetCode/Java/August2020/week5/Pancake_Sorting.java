public class Pancake_Sorting {
    public List<Integer> pancakeSort(int[] A) {
        List<Integer> output = new ArrayList<>();
        
        for(int i = A.length; i > 0; i--){
            int index = find(A, i);
            
            if(index == i-1)
                continue;
            if(index != 0){
                output.add(index+1);
                this.flip(A, index+1);
            }
            output.add(i);
            flip(A, i);
        }
        
        return output;
    }
    
    public void flip(int[] A, int k){
        int i = 0;
        while (i < k / 2){
            int temp = A[i];
            A[i] = A[k-i-1];
            A[k-i-1] = temp;
            i += 1;
        }
    }
    
    public int find(int[] A, int target){
        for(int i = 0; i < A.length; i++)
            if(A[i] == target)
                return i;
        return -1;
    }
}