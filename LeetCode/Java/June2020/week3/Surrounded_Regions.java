public class Surrounded_Regions{
    public void solve(char[][] board) {
        /*
            int[][] helper = new int[board.length][board.length];
            loop through the top
                if board[0][i] == 'O'
                    helper[0][i] = 1
                    dfs(board, 0, i)
            loop through the right side
                if board[i][board[0].length-1] == 'O'
                    dfs(board, i, board.length[0]-1)
            loop through the bottom side
                if board[board.length-1][i] == 'O'
                    dfs(board, board.length-1, i)
            loop through left side
                if board[i][0]
                    dfs(board, i, 0)
            
            for i = 1; i<board.length; i++
                for j = 1; j < board[0].length; j++
                    if(board[i][j] == 'O')
                        board[i][j] = 'X';
                    
            void dfs(char[][] board, int[][] helper, int row, int col)
                if((row < 0 || row >= board.length) || (col < 0 || col >= board[0].length) || board[row][col] != 'O')
                    return
                board[row][col] = '*'
                dfs(board, row-1, col);
                dfs(board, row+1, col);
                dfs(board, row, col-1);
                dfs(board, row, col+1);
        */
        if((board.length == 0 || board[0].length == 0) || board.length < 2 || board[0].length < 2)
            return;
        
        int n = board.length;
        int m = board[0].length;
        for(int i = 0; i < n; i++){
            if(board[i][0] == 'O')
                dfs(board, i, 0);
            if(board[i][m-1] == 'O')
                dfs(board, i, m-1);
        }
        for(int i = 0; i < m; i++){
            if(board[0][i] == 'O')
                dfs(board, 0, i);
            if(board[n-1][i] == 'O')
                dfs(board, n-1, i);
        }
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                else if(board[i][j] == '*')
                    board[i][j] = 'O';
    }
    
    private void dfs(char[][] board, int row, int col){
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O')
            return;
        board[row][col] = '*';
        dfs(board, row-1, col);
        dfs(board, row, col-1);
        dfs(board, row+1, col);
        dfs(board, row, col+1);
        
    }
}