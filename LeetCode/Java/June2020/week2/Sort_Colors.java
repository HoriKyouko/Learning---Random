public class Sort_Colors {
    public void sortColors(int[] nums) {
        int zero = 0;
        int two = nums.length-1;
        int p = 0;
        
        while(p <= two && zero < two){
            if(nums[p] == 2){
                nums[p] = nums[two];
                nums[two] = 2;
                two--;
            }
            else if(nums[p] == 0){
                nums[p] = nums[zero];
                nums[zero] = 0;
                zero++;
                p++;
            }
            else{
                p++;
            }
        }
    }
}