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
        int [] subcount = new int[26];
        List<Integer> output = new ArrayList();
        
        for(char c: p.toCharArray()) count[c-'a']++;
        
        for(int i = 0; i < s.length(); i++){
            subcount[s.charAt(i) - 'a']++;
            if(i >= p.length())
                subcount[s.charAt(i-p.length())-'a']--;
            if(Arrays.equals(count,subcount))
                output.add(i - p.length() + 1);
        }
        
        return output;
    }
}