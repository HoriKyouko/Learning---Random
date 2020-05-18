impl Solution {
    pub fn max_sub_array(nums: Vec<i32>) -> i32 {
        let mut current_sum = nums[0];
        let mut max_sum = nums[0];
        let mut i :usize = 1;
        while i < nums.len(){
            let temp:i32 = current_sum + nums[i];
            current_sum = temp.max(nums[i]);
            if current_sum > max_sum{
                max_sum = current_sum;
            }
            i += 1;
        }
        max_sum
    }
}