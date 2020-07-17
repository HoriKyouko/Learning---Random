public class Top_K_Frequent_Elements {
    int [] unique;
    HashMap<Integer, Integer> map;
    public int[] topKFrequent(int[] nums, int k) {
        /*
            HashMap for frequency, Quick Select for getting the Top k elements.
        */
        map = new HashMap();
        for(int i: nums)
            map.put(i, map.getOrDefault(i, 0) + 1);
        
        int n = map.size();
        unique = new int[n];
        int i = 0;
        for(int num: map.keySet()){
            unique[i] = num;
            i++;
        }
        
        quickSelect(0, n-1, n-k);
        
        return Arrays.copyOfRange(unique, n-k, n);
    }
    
    private void quickSelect(int left, int right, int kth){
        if(left == right) return;
        
        Random rand = new Random();
        int pivot = left + rand.nextInt(right-left);
        
        pivot = partition(left, right, pivot);
        
        if(kth == pivot)
            return;
        else if(kth < pivot)
            quickSelect(left, pivot-1, kth);
        else
            quickSelect(pivot+1, right, kth);
    }
    
    public int partition(int left, int right, int pivot){
        int pivotFrequency = map.get(unique[pivot]);
        
        swap(pivot, right);
        int storedIndex = left;
        
        for(int i = left; i <= right; i++){
            if(map.get(unique[i]) < pivotFrequency){
                swap(storedIndex, i);
                storedIndex++;
            }
        }
        swap(storedIndex, right);
        return storedIndex;
    }
    
    public void swap(int a, int b){
        int temp = unique[a];
        unique[a] = unique[b];
        unique[b] = temp;
    }
}