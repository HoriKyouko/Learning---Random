public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList<>();
        // Add the empty list
        output.add(new ArrayList<>());
        // Loop through our given array
        for(int i : nums){
            // create a new List of List of ints
            List<List<Integer>> subset = new ArrayList<>();
            // Loop through our current output and add i to whatever our output currently has and add the new one to the subset
            for(List<Integer> l : output){
                List<Integer> temp = new ArrayList<>(l);
                temp.add(i);
                subset.add(temp);
            }
            // Adding all our new subsets to our output set.
            for(List<Integer> l : subset)
                output.add(l);
        }
        return output;
    }
}