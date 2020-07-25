public class Single_Number_III {
    public int[] singleNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i: nums)
            map.put(i, map.getOrDefault(i, 0) + 1);
        int[] val = new int[2];
        int count = 0;
        for(int i: nums)
            if(map.get(i)== 1)
                val[count++] = i;
        return val;
    }
}