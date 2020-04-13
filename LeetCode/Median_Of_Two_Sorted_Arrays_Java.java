class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        /*
        ORIGINAL SOLUTION!

        int[] temp = new int[nums1.length + nums2.length];
        int j = 0; int k = 0;
        double finalReturn = 0.0;
        for(int i = 0; i < temp.length; i++){
            if(j < nums1.length && k < nums2.length){
                if(nums1[j] <= nums2[k]){
                    temp[i] = nums1[j];
                    j++;
                }
                else{
                    temp[i] = nums2[k];
                    k++;
                }
            }
            else if(j < nums1.length){
                temp[i] = nums1[j];
                j++;
            }
            else{
                temp[i] = nums2[k];
                k++;
            }
        }
        double t = (double)temp.length / 2;
        int te = temp.length / 2;
        if((double)te < t){ // its odd
            finalReturn = temp[te];
        }
        else{// even
            finalReturn = (double)(temp[te-1] + temp[te])/2;
        }
        return finalReturn;*/
        
        int m = nums1.length, n = nums2.length;
        if(m > n){
            int [] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
            
            int t = m;
            m = n;
            n = t;
        }
        
        int iMin = 0, iMax = m;
        while(iMin <= iMax){
            int i = (iMin + iMax)/2;
            int j = ((m + n + 1)/2) - i;
            
            if(i < iMax && nums2[j-1] > nums1[i]){
                iMin = i + 1;
            }
            else if(i > iMin && nums1[i-1] > nums2[j]){
                iMax = i - 1;
            }
            else{
                int maxLeft = 0;
                if(i == 0){
                    maxLeft = nums2[j-1];
                }
                else if(j == 0){
                    maxLeft = nums1[i-1];
                }
                else{
                    maxLeft = Math.max(nums1[i-1], nums2[j-1]);
                }
                if((m+n) % 2 == 1){
                    return maxLeft;
                }

                int minRight = 0;
                if(i == m){
                    minRight = nums2[j];
                }
                else if(j == n){
                    minRight = nums1[i];
                }
                else{
                    minRight = Math.min(nums2[j], nums1[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}