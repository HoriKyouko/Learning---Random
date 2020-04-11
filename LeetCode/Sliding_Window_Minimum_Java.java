class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Queue<Integer> q = new LinkedList<>();
        int l = 0; int maxIndex = 0; int max = Integer.MIN_VALUE;
        
        for(int r = 0; r < nums.length; r++){
            if(r-l == k-1){
                if(maxIndex < l){
                    int temp = l;
                    max = nums[temp];
                    while(temp < r){
                        if(nums[temp] > max){
                            max = nums[temp];
                            maxIndex = l;
                        }
                        temp++;
                    }
                }
                if(nums[r] > max){
                    max = nums[r];
                    maxIndex = r;
                }
                q.add(max);
                l++;
            }
            else if(nums[r] > max){
                max = nums[r];
                maxIndex = r;
            }
        }
        
        int len = q.size();
        int [] val = new int[len];
        for(int j = 0; j < len; j++)
            val[j] = q.remove();
        
        return val;
    }
}