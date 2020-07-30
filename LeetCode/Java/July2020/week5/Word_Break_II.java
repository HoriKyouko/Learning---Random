public class Word_Break_II {
    int maxLen = 0;
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        for(String w : wordDict)
            maxLen = Math.max(maxLen, w.length());
        
        Map<Integer, List<String>> map = new HashMap<>();
        return dfs(s, set, map, 0);
    }
    
    List<String> dfs(String s, Set<String> set, Map<Integer, List<String>> map, int start){
        if(map.containsKey(start))
            return map.get(start);
        List<String> list = new ArrayList<>();
        if(s.length() == start )
            list.add("");
        for(int end = start; end<s.length() && end<start+maxLen; end++){
            if(set.contains(s.substring(start, end+1))){
                List<String> nexts = dfs(s, set, map, end+1);
                for(String next : nexts)
                    if(next == "")
                        list.add(s.substring(start,end+1) + next);
                    else
                        list.add(s.substring(start,end+1) + " " + next);                
            }
            map.put(start, list);
        }
        return list;
    }
}
