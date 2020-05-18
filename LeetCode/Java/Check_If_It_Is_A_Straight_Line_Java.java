class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        if(coordinates.length == 2) return true;
        
        float dy = coordinates[1][1] - coordinates[0][1];
        float dx = coordinates[1][0] - coordinates[0][0];
        float slope = dy/dx;
        
        for(int i = 1; i < coordinates.length - 1; i++){
            float temp = (float)(coordinates[i+1][1] - coordinates[i][1])/(float)(coordinates[i+1][0] - coordinates[i][0]);
            if(temp != slope) return false;
        }
        
        return true;
    }
}