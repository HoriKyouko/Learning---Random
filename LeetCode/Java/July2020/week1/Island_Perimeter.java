public class Island_Perimeter {
    public int islandPerimeter(int[][] grid) {
        int outs = 0;
        int ins = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    outs++;
                    if(i < grid.length-1 && grid[i+1][j] == 1) ins++;
                    if(j < grid[0].length-1 && grid[i][j+1] == 1) ins++;
                }
            }
        }
        return outs*4 - ins*2;
    }
}