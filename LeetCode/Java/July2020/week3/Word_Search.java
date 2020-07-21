public class Word_Search {
    boolean[][] visited;
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                // dfs from start and see if word exists.
                // if so return true;
                if(board[i][j] == word.charAt(0))
                    if(dfs(board, i, j, word, 0))
                        return true;
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int i, int j, String word, int pos){
        if(pos == word.length())
            return true;
        
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(pos) || visited[i][j]){
            return false;
        }
        visited[i][j] = true;
        boolean top = dfs(board, i+1, j, word, pos+1);
        boolean bottom = dfs(board, i-1, j, word, pos+1);
        boolean left = dfs(board, i, j-1, word, pos+1);
        boolean right = dfs(board, i, j+1, word, pos+1);
        
        if(top || bottom || left || right)
            return true;
        
        visited[i][j] = false;
        return false;
    }
}