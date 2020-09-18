public class Is_Graph_Bipartite {
    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        
        for(int i = 0; i < graph.length; i++){
            if(color[i] != 0)
                continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            color[i] = 1;
            while(!queue.isEmpty()){
                int n = queue.remove();
                for(int next : graph[n]){
                    if(color[next] == 0){
                        color[next] = -color[n];
                        queue.add(next);
                    }
                    else if(color[next] != -color[n]){
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
}
