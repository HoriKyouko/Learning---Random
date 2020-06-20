public class Permutation_Sequence {
    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        int[] fact = new int[n+1];
        StringBuilder sb = new StringBuilder();
        
        fact[0] = 1;
        for(int i = 1; i <= n; i++){
            fact[i] = fact[i-1] * i;
            nums.add(i);
        }
        
        k--;
        for(int i = 1; i <= n; i++){
            int index = k/fact[n-i];
            sb.append(nums.get(index));
            nums.remove(index);
            k -= index * fact[n-i];
        }
        
        return sb.toString();
    }
}