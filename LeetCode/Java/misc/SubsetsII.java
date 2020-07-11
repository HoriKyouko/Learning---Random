public class SubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        output.add(new ArrayList<>());
        Arrays.sort(nums);
        int size = 0;
        for(int i = 0; i < nums.length; i++){
            int startIndex = (i >= 1 && nums[i] == nums[i-1]) ? size : 0;
            size = output.size();
            for(int j = startIndex; j < size; j++){
                List<Integer> temp = new ArrayList<>(output.get(j));
                temp.add(nums[i]);
                output.add(temp);
            }
        }
        return output;
    }
}