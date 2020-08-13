public class Iterator_For_Combinations {
    Queue<String> q = new LinkedList<>();
    public CombinationIterator(String characters, int combinationLength) {
        preprocess(characters, combinationLength, 0, new StringBuilder());
    }
    
    public String next() {
        return q.poll();
    }
    
    public boolean hasNext() {
        return !q.isEmpty();
    }
    
    private void preprocess(String characters, int combinationLength, int start, StringBuilder res){
        if(combinationLength == 0){
            q.add(res.toString());
            return;
        }
        
        for(int i = start; i <= characters.length() - combinationLength; i++){
            res.append(characters.charAt(i));
            preprocess(characters, combinationLength-1, i+1, res);
            res.deleteCharAt(res.length()-1);
        }
    }
}