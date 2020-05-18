class Solution {
    public boolean backspaceCompare(String S, String T) {
        StringBuilder str = new StringBuilder();
        StringBuilder str2 = new StringBuilder();
        
        for(int i = 0; i < S.length(); i++){
            if(S.charAt(i) == '#' && str.length() == 0)
                continue;
            else if(S.charAt(i) != '#')
                str.append(S.charAt(i));
            else if(S.charAt(i) == '#')
                str.delete(str.length()-1, str.length());
        }
        
        for(int i = 0; i < T.length(); i++){
            if(T.charAt(i) == '#' && str2.length() == 0)
                continue;
            else if(T.charAt(i) != '#')
                str2.append(T.charAt(i));
            else if(T.charAt(i) == '#')
                str2.delete(str2.length()-1, str2.length());
        }
        
        return str.toString().equals(str2.toString());
    }
}