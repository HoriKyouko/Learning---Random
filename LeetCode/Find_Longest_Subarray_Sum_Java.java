/**
    Found this problem and it's solution here: https://www.youtube.com/watch?v=XFPHg5KjHoo

    Utilizes a sliding window algorithm in order to find the longest subarray.

    Example: nums = [1,2,3,7,5], s = 12

    Printout of values after right++;

    1st run: 
    left = 0, right = 1, sum = 1 result = {-1}

    2nd run: 
    left = 0, right = 2, sum = 3 result = {-1}

    3rd run: 
    left = 0, right = 3, sum = 6 result = {-1}

    This next run involves the sum going over so we enter our second while loop.
    This moves our left side of the window over one and we reevaluate the sum.

    4th run: 
    left = 1, right = 4, sum = 12 result = {2,4}

    This next run involves the sum going over so we enter our second while loop.
    This moves our left side of the window over one and we reevaluate the sum.
    This occurs until our left is in the 3rd index where we equal the sum again.
    This is another possible subarray so our if condition is checked, but it is
    found that the original 4-2 > 4-3 so we do not enter the condition.

    5th run: 
    left = 3, right = 5, sum = 12 result = {2,4}

    Returned value: {2,4}
 */
class longestSubArraySum{
    int[] findLongestSubarraySum(int[] nums, int s){
        int[] result = new int[] {-1};
    
        int sum = 0;
        int left = 0;
        int right = 0;
    
        while(right < nums.length){
            sum += nums[right];
            while(left < right && sum > s){
                sum -= nums[left++];
            }
            if(sum == s && (result.length == 1 || result[1]-result[0] < right - left)){
                result = new int[]{left + 1, right + 1};
            }
            right++;
        }
        return result;
    }
}
