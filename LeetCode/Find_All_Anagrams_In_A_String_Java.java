class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        /*
            Find anagram value for p
            
            loop through s until i <= s.length()-p.length(); and for i -> i+p.length() check that
            substrings anagram value
            
            if substring value is equal to p value add i to output List
            else increment i.
            
            
        */
        int [] count = new int[26];
        List<Integer> output = new ArrayList();
        
        for(char c: p.toCharArray()) count[c-'a']++;
        
        int len = s.length()-p.length();
        
        for(int i = 0; i <= len; i++){
            int [] subcount = new int[26];
            
            for(char c: s.substring(i, i+p.length()).toCharArray()) subcount[c-'a']++;

            if(Arrays.equals(count,subcount))
                output.add(i);
        }
        
        return output;
    }
}