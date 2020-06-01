public class Maximal_Square {
    public int maximalSquare(char[][] matrix) {
        if(matrix.length == 0)
            return 0;
        if(matrix[0].length == 0)
            return 0;
        
        int row = matrix.length;
        int col = matrix[0].length;
        int output = 0;
        int [][] mat = new int[row][col];
        
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(i == 0 || j == 0){
                    if(matrix[i][j] == '1'){
                        mat[i][j] = 1;
                        output = Math.max(output, mat[i][j]);
                    }
                }
                else{
                    if(matrix[i][j] == '1'){
                        mat[i][j] = Math.min(Math.min(mat[i][j-1],mat[i-1][j]), mat[i-1][j-1]) + 1;
                        output = Math.max(output, mat[i][j]);
                    }
                }  
            }
        }
        return output * output;
    }
}