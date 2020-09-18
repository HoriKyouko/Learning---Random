public class Minimum_Number_Of_Vertices_To_Reach_All_Nodes {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        boolean [] seen = new boolean[n];
        List<Integer> output = new ArrayList<>();
        
        for(List<Integer> i : edges)
            seen[i.get(1)] = true;
        for(int i = 0; i < n; i++)
            if(seen[i] == false)
                output.add(i);
        
        return output;
    }
}
