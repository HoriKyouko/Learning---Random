public class Rotting_Oranges {
    public int orangesRotting(int[][] grid) {
        int n = grid.length; 
        int m = grid[0].length;
        int output = 0;
        int countFresh = 0;
        Queue<int[]> q = new LinkedList<>();
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 2)
                    q.add(new int[]{i,j});
                else if(grid[i][j] == 1)
                    countFresh++;
            }
        }
        
        if(countFresh == 0)
            return 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        
        while(!q.isEmpty()){
            output++;
            int size = q.size();
            for(int i = 0; i < size; i++){
                int[] point = q.poll();
                for(int[] dir : dirs){
                    int x = point[0] + dir[0];
                    int y = point[1] + dir[1];
                    
                    if(x < 0 || x >= n || y < 0 || y >= m || grid[x][y] != 1)
                        continue;
                    
                    grid[x][y] = 2;
                    q.add(new int[] {x, y});
                    countFresh--;
                }
            }
        }
        return countFresh == 0 ? output -1 : -1;
    }
}