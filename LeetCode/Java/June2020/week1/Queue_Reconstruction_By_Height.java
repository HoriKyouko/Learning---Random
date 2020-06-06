public class Queue_Reconstruction_By_Height {
    public int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        Arrays.sort(people, (a,b) -> a[0] == b[0] ? a[1]-b[1] : b[0]-a[0]);
        
        List<int[]> output = new ArrayList<>();
        
        for(int[] pep : people)
            output.add(pep[1], pep);
        
        return output.toArray(new int[len][2]);
    }
}