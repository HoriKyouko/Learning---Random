class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List> ans = new HashMap<String, List>();
        int [] count = new int[26];

        for(String s : strs){
            
            Arrays.fill(count, 0);
            for(char c : s.toCharArray())
                count[c - 'a']++;
            
            StringBuilder str = new StringBuilder();
            for(int i = 0; i < 26; i++){
                str.append('#');
                str.append(count[i]);
            }
            String key = str.toString();
            
            if(!ans.containsKey(key)) ans.put(key, new ArrayList());

            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}