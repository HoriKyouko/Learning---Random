public class Replace_Elements_With_Greatest_Element_On_Right_Side {
    public int[] replaceElements(int[] arr) {
        int [] output = new int[arr.length];
        int max = arr[arr.length-1];
        output[arr.length-1] = -1;
        for(int i = arr.length-2; i >= 0; i--){
            output[i] = max;
            max = Math.max(max, arr[i]);
        }
        
        return output;
    }
}