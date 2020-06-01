public class Implement_Trie {
    /*GO BACK AND RE DO THIS THE WAY THE ARTICLE SAYS TO DO IT!*/

    /**Need to implement a trie in a prefix tree */
    class TrieNode{
        HashMap<Character, TrieNode> children;
        boolean end;
        
        public TrieNode(){
            children = new HashMap();
            end = false;
        }
    }

    class Trie {
        TrieNode root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }
        
        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode node = root;
            for(char c: word.toCharArray()){
                if(!node.children.containsKey(c))
                    node.children.put(c, new TrieNode());
                node = node.children.get(c);
            }
            node.end = true;
        }
        
        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode node = root;
            for(char c: word.toCharArray()){
                if(!node.children.containsKey(c))
                    return false;
                node = node.children.get(c);
            }
            return node.end;
        }
        
        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for(char c: prefix.toCharArray()){
                if(!node.children.containsKey(c))
                    return false;
                node= node.children.get(c);
            }
            return true;
        }
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */
}