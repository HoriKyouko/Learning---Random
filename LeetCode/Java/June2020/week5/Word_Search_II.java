public class Word_Search_II {
    public class TrieNode{
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> output = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++)
                dfs(root, output, board, i, j);

        return output;
    }
    
    private void dfs(TrieNode root, List<String> output, char[][] board, int i, int j){
        char c = board[i][j];
        if(c == '#' || root.next[c-'a'] == null)
            return;
        root = root.next[c-'a'];
        
        if(root.word != null){
            output.add(root.word);
            root.word = null;
        }
        
        board[i][j] = '#';
        if(i > 0)
            dfs(root, output, board, i-1, j);
        if(j > 0)
            dfs(root, output, board, i, j-1);
        if(i < board.length - 1)
            dfs(root, output, board, i+1, j);
        if(j < board[0].length-1)
            dfs(root, output, board, i, j+1);
        
        board[i][j] = c;
    }
    
    private TrieNode buildTrie(String[] words){
        TrieNode root = new TrieNode();
        for(String s : words){
            TrieNode temp = root;
            for(char c : s.toCharArray()){
                int i = c - 'a';
                if(temp.next[i] == null)
                    temp.next[i] = new TrieNode();
                temp = temp.next[i];
            }
            temp.word = s;
        }
        return root;
    }
}