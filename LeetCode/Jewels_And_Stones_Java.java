class Solution {
    public int numJewelsInStones(String J, String S) {
        if(S.length() == 0) return 0;

        Map<Character, Character> map = new HashMap<>();
        for(char c : J.toCharArray())
            map.put(c, c);
        int output = 0;
        for(char s: S.toCharArray())
            if(map.containsKey(s)) output++;
        return output;
        
    }
}