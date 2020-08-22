public class Sort_Array_By_Parity {
    public int[] sortArrayByParity(int[] A) {      
        int [] output = new int[A.length];
        List<Integer> odd = new ArrayList<>();
        int c = 0;
        
        for(int i : A){
            if(i % 2 == 0)
                output[c++] = i;
            else
                odd.add(i);
        }
        
        for(int i : odd)
            output[c++] = i;        
        return output;
    }
}