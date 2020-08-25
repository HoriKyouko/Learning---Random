public class Stream_Of_Characters {
    // Create a TrieNode class and what that class will contain.
    class TrieNode{
        boolean isWord;
        TrieNode[] next = new TrieNode[26];
    }
    // Root of the Trie
    TrieNode root = new TrieNode();
    // StringBuilder that will contain every query character from beginning to end.
    StringBuilder sb = new StringBuilder();

    // Constructor that will build our Trie from the given words.
    public StreamChecker(String[] words) {
        createWords(words);
    }
    // Query that looks through our StringBuilder and determines if the current character
    // were querying is the end of a word in our Trie return true else false.
    public boolean query(char letter) {
        // adds the current letter to the end of the SB.
        sb.append(letter);
        // gets the root of the Trie for traversal.
        TrieNode t = root;
        // Loops from the end of the SB so long as we don't encounter null && were greater than or equal to 0.
        for(int i = sb.length() - 1; i >= 0 && t != null; i--){
            char c = sb.charAt(i);
            // goes to that Node.
            t = t.next[c-'a'];
            // Checks if that node exists and if it's the end of a word.
            if(t != null && t.isWord)
                return true;
        }
        return false;
    }
    // Adds our words to the Trie, but backwards since we have to search the SB
    // backwards.
    private void createWords(String[] words){
        // loops through the words
        for(String s : words){
            // gets a specific words length
            int len = s.length();
            // gets our root position to traverse and manipulate.
            TrieNode t = root;
            // loops over the word from the end to the beginning.
            for(int i = len-1; i >= 0; i--){
                char c = s.charAt(i);
                // checks if our node exists or not.
                if(t.next[c-'a'] == null)
                    // if it doesn't we create a new node there
                    t.next[c-'a'] = new TrieNode();
                // traverse to the next node.
                t = t.next[c-'a'];
            }
            // Once we've reached the beginning of the word we mark it's end.
            t.isWord = true;
        }
    }
}