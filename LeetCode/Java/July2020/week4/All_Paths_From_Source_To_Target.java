public class All_Paths_From_Source_To_Target {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> output = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        
        path.add(0);
        dfs(graph, 0, path, output);
        return output;
    }
    
    private void dfs(int[][] graph, int node, List<Integer> path, List<List<Integer>> output){
        if(node == graph.length-1){
            output.add(new ArrayList<>(path));
            return;
        }
        
        for(int i : graph[node]){
            path.add(i);
            dfs(graph, i, path, output);
            path.remove(path.size()-1);
        }
    }
}