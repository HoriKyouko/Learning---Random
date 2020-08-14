public class Longest_Palindrome{
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        HashSet<Character> set = new HashSet();
        for(char c : s.toCharArray()){
            if(map.containsKey(c)){
                map.put(c, map.get(c) + 1);
            }
            else{
                map.put(c, 1);
                set.add(c);
            }
        }
        
        int output = 0;
        boolean isOdd = false;
        // iterate over map and 
        for(char c : set){
            int freq = map.get(c);
            if(freq % 2 == 0){
                output += freq;
            }
            else{
                output += freq-1;
                isOdd = true;
            }
        }
        
        return (isOdd) ? output + 1 : output;
    }
}