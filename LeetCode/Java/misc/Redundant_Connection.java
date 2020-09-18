public class Redundant_Connection {
    public int[] findRedundantConnection(int[][] edges) {
        DisjointSet ds = new DisjointSet(edges.length);
        
        for(int[] edge : edges)
            if(!ds.union(edge[0]-1, edge[1]-1))
                return edge;
        
        return new int[]{0,0};
    }
    
    class DisjointSet{
        int [] parent;
        int [] rank;
        
        public DisjointSet(int n){
            parent = new int[n];
            rank = new int[n];
        }
        
        private int find(int x){
            if(parent[x] == 0)
                return x;
            return parent[x] = find(parent[x]);
        }
        
        public boolean union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY)
                return false;
            if(rank[rootX] < rank[rootY])
                parent[rootX] = rootY;
            else if(rank[rootX] > rank[rootY])
                parent[rootY] = rootX;
            else{
                parent[rootX] = rootY;
                rank[rootY]++;
            }
            
            return true;
        }
    }
}
