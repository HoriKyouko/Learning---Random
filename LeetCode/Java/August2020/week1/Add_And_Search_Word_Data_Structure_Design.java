public class Add_And_Search_Word_Data_Structure_Design {
    class TrieNode{
        HashMap<Character, TrieNode> children;
        boolean end;

        public TrieNode(){
            children = new HashMap();
            end = false;
        }
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode node = root;
        for(char c: word.toCharArray()){
            if(!node.children.containsKey(c))
                node.children.put(c, new TrieNode());
            node = node.children.get(c);
        }
        node.end = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return searchAllChildren(root, word.toCharArray(), 0);
    }
    
    public boolean searchAllChildren(TrieNode node, char[] word, int i) {
        if(i == word.length)
            return node.end;
        for(int j = i; j < word.length; j++){
            if(word[j] == '.'){
                for(Character n : node.children.keySet())
                    if(node.children.get(n) != null && searchAllChildren(node.children.get(n), word, j+1))
                            return true;
            }
            if(!node.children.containsKey(word[j]))
                return false;
            node = node.children.get(word[j]);
        }
        
        return node.end;
    }
}