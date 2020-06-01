public class Number_Of_Islands {
    public int numIslands(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    findIsland(grid, i, j);
                    count++;
                }
            }    
        }
        return count;
    }
    
    private void findIsland(char[][] grid, int i, int j){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length){
            return;
        }
        if(grid[i][j] == '0'){
            return;
        }
        
        grid[i][j] = '0';
        findIsland(grid, i-1, j);
        findIsland(grid, i+1, j);
        findIsland(grid, i, j-1);
        findIsland(grid, i, j+1);
        
    }
}