public class Is_Subsequence{
    public boolean isSubsequence(String s, String t) {
        if(s.length() == 0)
            return true;
        int j = 0;
        for(char c : t.toCharArray())
            if(j < s.length() && c == s.charAt(j))
                j++;
        
        return j == s.length();
    }
}