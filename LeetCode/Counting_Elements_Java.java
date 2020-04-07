class Solution {
    public int countElements(int[] arr) {
        /* First thought solution:
        
            Take the given array and sort it.
            Then run through it checking if
            arr[i+1] = x+1
            
            If arr[i+1] = x then we need to increment
            a counter until we either hit x+1 or if
            not then we reset it.
            
            Runtime = O(nlogn)
            Space = O(1)
        */
        
        /*int val = 0;
        int count = 1;
        Arrays.sort(arr);
        
        for(int i = 1; i < arr.length; i++){
            if(arr[i]-1 == arr[i-1]){
                val += count;
                count = 1;
            }
            else if(arr[i] == arr[i-1]){
                count++;
            }
            else if(arr[i] != arr[i-1] && count > 1){
                count = 1;
            }
        }
        return val;*/
        
        /* Optimized Solution:
        
            Create a Hashset to store all values in the given array
            
            Run through the array and see if the set contains i+1.
            If it does increment a counter variable else just move
            to the next integer in the given array.
            
            Return counter variable.
            
            Runtime = O(n)
            Space = O(n)
            
            Trade off of space for quicker runtime.
        */
        HashSet<Integer> set = new HashSet<>();
        int val = 0;
        for(int i : arr)
            set.add(i);
        
        for(int i: arr)
            if(set.contains(i+1))
                val++;
        
        return val;
    }
}